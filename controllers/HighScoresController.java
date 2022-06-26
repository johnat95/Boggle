package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.HighScore;
import model.BaseScreen;
import model.BoggleHighScoreStorage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class HighScoresController extends BaseScreen {

    private ArrayList<HighScore> sortedArray = new ArrayList<>();

    private BoggleHighScoreStorage boggleHighScoreStorage;




    @FXML
    private GridPane highScoreDataTable;
    @FXML
    private Button menuReturnButton;

    //events
    EventHandler<ActionEvent> menuReturn = event -> menuReturn();

    public HighScoresController(){


    }

    public void setSaveData(BoggleHighScoreStorage d){
        this.boggleHighScoreStorage = d;
    }

    public void initialize(){
        menuReturnButton.setOnAction(menuReturn);



    }


    private void getScoreArray(){
        this.sortedArray = boggleHighScoreStorage.getHighScoreArrayList();
    }


    public void populateTable() {
        getScoreArray();

        AtomicInteger col = new AtomicInteger(0);
        AtomicInteger row = new AtomicInteger(1);

        sortedArray.forEach(e -> {

            Label temp = new Label(e.getName());
            GridPane.setConstraints(temp, col.get(), row.get());
            highScoreDataTable.getChildren().add(temp);
            col.getAndIncrement();

            temp = new Label(String.valueOf(e.getScore()));
            GridPane.setConstraints(temp, col.get(), row.get());
            highScoreDataTable.getChildren().add(temp);
            col.getAndIncrement();

            temp = new Label(e.getDateTimeString());
            GridPane.setConstraints(temp, col.get(), row.get());
            highScoreDataTable.getChildren().add(temp);
            col.set(0);
            row.getAndIncrement();

        });


    }



}
