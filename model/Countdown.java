package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Countdown{
     private final int totalSeconds;
     private Timeline timeline;
     private AtomicInteger secondsCounter = new AtomicInteger();
     private ArrayList<CountdownObserver> countdownObserverArrayList = new ArrayList<>();

     public SimpleStringProperty countdownString = new SimpleStringProperty();

     //event
     EventHandler<ActionEvent> gameplayCountdown = (event) -> {


         //build time remaining string
         StringBuilder sb = new StringBuilder();
         sb.append(secondsCounter.get() / 60).append(":");
         if (secondsCounter.get() % 60 < 10) {
             sb.append("0");
         }
         sb.append(secondsCounter.get() % 60);
         Platform.runLater(() -> countdownString.set(sb.toString()));


         secondsCounter.getAndDecrement();

         if (secondsCounter.get() < 0) {
             timeline.stop();
             Platform.runLater(() -> notifyObserver(true));

         }
     };

    public Countdown(int minutes, int seconds){
        this.totalSeconds = minutes*60+seconds-1;

        countdownString.set("3:00");

        this.timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), gameplayCountdown);
        timeline.getKeyFrames().add(frame);
    }


    public void startCountdown(){
        secondsCounter.set(totalSeconds);
        timeline.playFromStart();
    }

    public void pauseCountdown(){
        timeline.pause();
    }

    public void resumeCountdown(){
        timeline.play();
    }

    public void stopAndResetCountdown(){
        countdownString.set("3:00");
        timeline.stop();
    }

    public void addCountdownObserver(CountdownObserver o){
        this.countdownObserverArrayList.add(o);
    }

    private void notifyObserver(boolean u){
        for(CountdownObserver o : countdownObserverArrayList){
            o.update(u);
        }
    }

}
