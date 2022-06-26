package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class MainMenuController {
    private Stage primaryStage;
    //Scenes
    private Parent playScreen;
    private Parent highScoreScreen;
    private HighScoresController hsc;


    //FXML
    @FXML
    private Label playButton;
    @FXML
    private Label highScoresButton;
    @FXML
    private Label exitButton;

    //events
    EventHandler<MouseEvent> playMakeWord = event -> loadPlayScreen();

    EventHandler<MouseEvent> highScoreEvent = event -> loadHighScoreScreen();

    EventHandler< MouseEvent> exit = event -> exit();

    public void initialize(){
        playButton.setOnMouseClicked(playMakeWord);
        highScoresButton.setOnMouseClicked(highScoreEvent);
        exitButton.setOnMouseClicked(exit);
    }


    public void setPrimaryStage(Stage p){
        this.primaryStage = p;
    }

    public void setPlayScene(Parent p){
        this.playScreen = p;
    }
    public void setHighScoreScreen(Parent p){
        this.highScoreScreen = p;
    }
    public void setHighScoresController(HighScoresController c){
        this.hsc = c;
    }

    private void loadPlayScreen(){
        //if not using getPrimaryStage
        //Stage primaryStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        primaryStage.getScene().setRoot(playScreen);

    }

    private void loadHighScoreScreen(){
        primaryStage.getScene().setRoot(highScoreScreen);
        hsc.populateTable();


    }


    @FXML
    private void exit(){
        Platform.exit();
    }
}
