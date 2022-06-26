package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.HighScore;



public class NewScoreDiaController {
    @FXML
    private TextField scoreName;


    public HighScore processHighScore(int score){

        return new HighScore(scoreName.getText(), score);

    }
}
