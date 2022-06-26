package model;

import javafx.application.Platform;

public class CountdownObserver {

    private Runnable toRun;

    public CountdownObserver(Runnable run){
        this.toRun=run;
    }

    public void update(boolean update){

        Platform.runLater(toRun);
    }

    
}
