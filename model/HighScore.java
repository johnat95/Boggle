package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class HighScore implements Comparable<HighScore> {
    private String name;
    private int score;
    private LocalDateTime dateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a");


    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
        this.dateTime = LocalDateTime.now();

    }

    public HighScore(String name, int score, LocalDateTime datetime) {
        this.name = name;
        this.score = score;
        this.dateTime = datetime;

    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDateTimeString() {
        return dateTime.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }

    @Override
    public int compareTo(HighScore o) {
        if (this.score == o.getScore()) {
            if (this.getDateTime().isBefore(o.getDateTime())) {
                return -1;
            }else{
                return 0;
            }
        } else if (this.score > o.getScore()) {
            return -1;
        } else {
            return 1;
        }
    }

}
