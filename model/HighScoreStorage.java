package model;



import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HighScoreStorage {

    //array list to store saves during run time
    private ArrayList<HighScore> highScoreArrayList = new ArrayList<>();

    //location of save file
    private String saveFileLoc;


//load save file, create if not found at location
    public HighScoreStorage(String saveFileLoc) {
        this.saveFileLoc = saveFileLoc;

        File saveFile = new File(saveFileLoc);
        if(!saveFile.exists()){
            try {
                saveFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //getter for HashMap containing scores

    public ArrayList<HighScore> getHighScoreArrayList(){
        return this.highScoreArrayList;
    }

    //add a new high score to high score array and sort using
    // HighScore compareTo method Reference.
    public void addScore(HighScore hs){
        highScoreArrayList.add(hs);
        if(highScoreArrayList.size()>0) {
            Collections.sort(highScoreArrayList, HighScore::compareTo);
        }
    }

    //write score data to save file
    public void writeScoreData(){
        try(DataOutputStream stream = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(saveFileLoc)))){
            for(HighScore hs : highScoreArrayList){
                stream.writeUTF(hs.getName());
                stream.writeInt(hs.getScore());
                stream.writeUTF(hs.getDateTimeString());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //load data from save file
    public boolean loadScoreData(){

        if(saveFileLoc.length()>0) {
            try (DataInputStream stream = new DataInputStream(new BufferedInputStream(
                    new FileInputStream(saveFileLoc)))) {
                boolean eof = false;
                while (!eof) {
                    try {

                        String name = stream.readUTF();
                        int score = stream.readInt();
                        String dateTimeString = stream.readUTF();

                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a");

                        //dateTime string parsed into LocalDateTime, HighScore constructor
                        //takes a LocalDateTime as param
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
                        HighScore hs = new HighScore(name, score, dateTime);


                        highScoreArrayList.add(hs);


                    } catch (EOFException e) {
                        eof = true;

                    }
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }



}
