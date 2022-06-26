package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Optional;


public class BoggleController extends BaseScreen{

    //fxml
    @FXML
    private GridPane playGridPaneRoot;
    @FXML
    private Button playMenuReturnButton;
    @FXML
    private Button playStartButton;
    @FXML
    private TextField wordSubmitLine;
    @FXML
    private Button playSubmitButton;
    @FXML
    private Button clearSubmitLine;
    @FXML
    private Button playPauseButton;
    @FXML
    private GridPane playBoardGridPane;
    @FXML
    private Label playTimeRemaining;
    @FXML
    private ScrollPane submittedWordsScroll;
    @FXML
    private VBox subWordsVbox;


    //FXML letter labels
    @FXML
    private Label d1;
    @FXML
    private Label d2;
    @FXML
    private Label d3;
    @FXML
    private Label d4;
    @FXML
    private Label d5;
    @FXML
    private Label d6;
    @FXML
    private Label d7;
    @FXML
    private Label d8;
    @FXML
    private Label d9;
    @FXML
    private Label d10;
    @FXML
    private Label d11;
    @FXML
    private Label d12;
    @FXML
    private Label d13;
    @FXML
    private Label d14;
    @FXML
    private Label d15;
    @FXML
    private Label d16;

    private ArrayList<Label> labelArray = new ArrayList<>();

    private ArrayList<String> letterList = new ArrayList<>();


    Runnable gameOver = new Runnable() {
        @Override
        public void run() {
            showGameOver(bgb.scoreGame());
        }
    };


    //countdown objects
    Countdown countdown = new Countdown(3,0);
    CountdownObserver countdownObserver = new CountdownObserver(gameOver);



    //score data storage
    public BoggleHighScoreStorage bhss;

    //class to verify submission validity
    private Verification verify = new Verification();

    //variables used for gameplay
    private final BoggleGameBoard bgb = new BoggleGameBoard();
    private boolean gameRunning;
    public Dice lastChosenDice;



    //event handlers
    EventHandler<ActionEvent> start = event -> start();

    EventHandler<ActionEvent> exit = event -> showQuitDialog();

    EventHandler<ActionEvent> wordSubmit = event -> submit();
    
    EventHandler<ActionEvent> pause = event -> pause();

    EventHandler<MouseEvent> getLetter = event -> addLetterToSubmitLine((Label) event.getSource());

    EventHandler<ActionEvent> clearSubmitText = event -> clearSubmit();



    //initialize button labels and events for labels
    public void initialize(){

        labelArray.add(d1);
        labelArray.add(d2);
        labelArray.add(d3);
        labelArray.add(d4);
        labelArray.add(d5);
        labelArray.add(d6);
        labelArray.add(d7);
        labelArray.add(d8);
        labelArray.add(d9);
        labelArray.add(d10);
        labelArray.add(d11);
        labelArray.add(d12);
        labelArray.add(d13);
        labelArray.add(d14);
        labelArray.add(d15);
        labelArray.add(d16);

        //Iterate through label array and a mouse_clicked event to get the label's letter
        for(Label l: labelArray){
            l.addEventHandler(MouseEvent.MOUSE_CLICKED, getLetter);
        }
        //attach event handlers to buttons
        playMenuReturnButton.setOnAction(exit);
        playStartButton.setOnAction(start);
        playSubmitButton.setOnAction(wordSubmit);
        playPauseButton.setOnAction(pause);
        clearSubmitLine.setOnAction(clearSubmitText);


        //submitted list
        submittedWordsScroll.setPannable(true);
        subWordsVbox.setPrefHeight(playBoardGridPane.getHeight());
        //countdown

        playTimeRemaining.textProperty().bind(countdown.countdownString);

        countdown.addCountdownObserver(countdownObserver);

        clearBoard();


    }

    public void setSaveData(BoggleHighScoreStorage d){
        this.bhss = d;
    }


    private void showGameOver(int score){
        Dialog<ButtonType> gameOverDialog = new Dialog<>();
        gameOverDialog.initOwner(playGridPaneRoot.getScene().getWindow());
        gameOverDialog.setTitle("Game Over!");
        gameOverDialog.setHeaderText("Time has run out!\n\t Here is your score: "+score+"\n\nEnter your name and " +
                "hit ok!");


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/newScoreDia.fxml"));

        try{
            gameOverDialog.getDialogPane().setContent(loader.load());
        }catch (IOException e){
            e.printStackTrace();
        }
        gameOverDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        gameOverDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> res = gameOverDialog.showAndWait();

        if(res.isPresent() && res.get().equals(ButtonType.OK)) {
            NewScoreDiaController scoreDiaController = loader.getController();
            HighScore temp = scoreDiaController.processHighScore(score);
            bhss.addScore(temp);
            bhss.writeScoreData();

            reset();


        }
            reset();

    }

    private void showQuitDialog(){
        countdown.pauseCountdown();
        Dialog<ButtonType> quitDialog = new Dialog<>();
        quitDialog.initOwner(playGridPaneRoot.getScene().getWindow());
        quitDialog.setTitle("Go Back to menu?");
        quitDialog.setHeaderText("If you quit, no progress will be saved.\n Do you want to quit?");

        quitDialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        quitDialog.getDialogPane().getButtonTypes().add(ButtonType.NO);

        Optional<ButtonType> res = quitDialog.showAndWait();
        if((res.isPresent()) && res.get().equals(ButtonType.YES)){
            reset();
            loadMenuScene();
        }else{
            quitDialog.close();
            countdown.resumeCountdown();

        }
    }
    private void pause(){
        countdown.pauseCountdown();
        Dialog<ButtonType> pauseDialog = new Dialog<>();
        pauseDialog.initOwner(playGridPaneRoot.getScene().getWindow());
        pauseDialog.setTitle("Paused");
        pauseDialog.setHeaderText("Game Paused");
        pauseDialog.setContentText("Do you want to start over? No progress will be saved.");

        pauseDialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        pauseDialog.getDialogPane().getButtonTypes().add(ButtonType.NO);

        Optional<ButtonType> res = pauseDialog.showAndWait();
        if(res.isPresent() && res.get().equals(ButtonType.YES)){
            pauseDialog.close();
            reset();

        }else{
            pauseDialog.close();
            countdown.resumeCountdown();
        }
    }




    private void loadMenuScene(){
        clearBoard();
        if(gameRunning) { 

            gameRunning = false;

        }
        menuReturn();

    }



    public void start(){

        if(!gameRunning) {
            fillBoard();

            countdown.startCountdown();
            wordSubmitLine.setText("");
            gameRunning = true;


        }
    }

    private void reset() {
        if (gameRunning) {
            countdown.stopAndResetCountdown();
            bgb.clearScoreList();
            clearBoard();
            subWordsVbox.getChildren().clear();
            lastChosenDice = null;
            gameRunning = false;

        }

    }






    private void addLetterToSubmitLine(Label l){
        //check dice is neighbor
        Dice newDice = bgb.getDiceFromDiceArray(Integer.parseInt(l.getId().substring(1)));

        if(lastChosenDice != null){
            if(verify.verifyNeighbor(lastChosenDice,newDice)){
                lastChosenDice = newDice;
                //add letter to submit line if dice is neighbor, do nothing if not
                wordSubmitLine.setText(wordSubmitLine.getText().concat(l.getText().toLowerCase()));
            }
        }else{
            lastChosenDice = newDice;
            wordSubmitLine.setText(wordSubmitLine.getText().concat(l.getText().toLowerCase()));

        }

    }

    private void clearSubmit(){
        wordSubmitLine.setText("");
        lastChosenDice = null;
    }


    public void submit(){
        if(!gameRunning || wordSubmitLine.getText().equals("")){
            return;
        }else if(bgb.inScoreList(wordSubmitLine.getText())){
            wordSubmitLine.setText("");
            return;
        }
        if(verify.inDictionary(wordSubmitLine.getText())){

            String text = wordSubmitLine.getText();
            Label label = new Label(text);
            subWordsVbox.getChildren().add(label);
            bgb.addToScoreList(text);

        }

        wordSubmitLine.setText("");
        lastChosenDice = null;

    }


    
    private void fillBoard() {
        bgb.genLetters();
        letterList = bgb.getDiceInPlay();
        int index = 0;
        for(Label l :labelArray){
            l.setText(letterList.get(index));
            index++;
        }

        gameRunning = true;

    }

    private void clearBoard() {

        for(Label l : labelArray){
            l.setText("");
        }

    }


}
