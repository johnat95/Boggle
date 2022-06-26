package model;

import java.util.ArrayList;
import java.util.Random;

public class Dice {

    private int diceNum;
    private String[] letters;
    private ArrayList<Integer> neighbors;

    public Dice(int diceNum, String[] letters, ArrayList<Integer> neighbors){
        this.diceNum = diceNum;
        this.letters = letters;
        this.neighbors = neighbors;
    }

    public String genLetter(){
        Random r = new Random();
        return letters[r.nextInt(6)];
    }

    public ArrayList<Integer> getNeighbors(){
        return this.neighbors;
    }
    public int getDiceNum(){
        return this.diceNum;
    }


}
