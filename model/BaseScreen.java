package model;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class BaseScreen {
    private Stage primaryStage;
    private Parent menuScreen;

    public void setMenuScreen(Parent p){
        this.menuScreen = p;
    }

    public void setPrimaryStage(Stage s) {
        this.primaryStage = s;
    }

    public void menuReturn(){
        primaryStage.getScene().setRoot(menuScreen);
    }
}
