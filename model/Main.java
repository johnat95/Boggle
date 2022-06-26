package model;

import controllers.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;




public class Main extends Application {

    //highScores
    private BoggleHighScoreStorage bhss = new BoggleHighScoreStorage("./src/resources/boggleScores.dat");


    //controllers
    private BoggleController bl;
    private HighScoresController hsc;
    private MainMenuController mmc;


    public Main() {
    }


    @Override
    public void init() {
        bhss.loadScoreData();


    }

    @Override
    public void start(Stage primaryStage) throws Exception{



        //root scene
        int height = (int) Screen.getPrimary().getBounds().getHeight();
        int width = (int) Screen.getPrimary().getBounds().getWidth();


        //menu screen creation
        FXMLLoader menuPaneLoader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
        Parent menuPane = menuPaneLoader.load();
        mmc = menuPaneLoader.getController();
        Scene rootScene = new Scene(menuPane,width, height);

        //play screen creation
        FXMLLoader boggleLoader = new FXMLLoader(getClass().getResource("../view/bogglePlay.fxml"));
        Parent playPane = boggleLoader.load();
        bl = boggleLoader.getController();


        //High Scores screen creation
        FXMLLoader highScorePaneLoader = new FXMLLoader(getClass().getResource("../view/HighScores.fxml"));
        Parent highScorePane = highScorePaneLoader.load();
        hsc = highScorePaneLoader.getController();


        //injecting to menu

        mmc.setPlayScene(playPane);
        mmc.setHighScoreScreen(highScorePane);
        mmc.setPrimaryStage(primaryStage);
        mmc.setHighScoresController(hsc);


        //injecting to play

        bl.setMenuScreen(menuPane);
        bl.setPrimaryStage(primaryStage);
        bl.setSaveData(bhss);
        bl.setSaveData(bhss);


        //injecting to high score
        hsc.setMenuScreen(menuPane);
        hsc.setPrimaryStage(primaryStage);
        hsc.setSaveData(bhss);


        //primaryStage
        primaryStage.setMaximized(true);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setScene(rootScene);

        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
