package model;


import java.util.*;

public class BoggleGameBoard {

    //Letters on each dice
    private final String[] d1Array = {"A","A","E","E","G","N"};
    private final String[] d2Array = {"A","B","B","J","O","O"};
    private final String[] d3Array = {"A","C","H","O","P","S"};
    private final String[] d4Array = {"A","F","F","K","P","S"};
    private final String[] d5Array = {"A","O","O","T","T","W"};
    private final String[] d6Array = {"C","I","M","O","T","U"};
    private final String[] d7Array = {"D","E","I","L","R","X"};
    private final String[] d8Array = {"D","E","L","R","V","Y"};
    private final String[] d9Array = {"D","I","S","T","T","Y"};
    private final String[] d10Array = {"E","E","G","H","N","W"};
    private final String[] d11Array = {"E","E","I","N","S","U"};
    private final String[] d12Array = {"E","H","R","T","V","W"};
    private final String[] d13Array = {"E","I","O","S","S","T"};
    private final String[] d14Array = {"E","L","R","T","T","Y"};
    private final String[] d15Array = {"H","I","M","N","U","Qu"};
    private final String[] d16Array = {"H","L","N","N","R","Z"};

    //neighbors of each dice

    private final ArrayList<Integer> dn1 = new ArrayList<>(Arrays.asList(2,5,6));
    private final ArrayList<Integer> dn2 = new ArrayList<>(Arrays.asList(1,5,6,3,7));
    private final ArrayList<Integer> dn3 = new ArrayList<>(Arrays.asList(2,6,7,8,4));
    private final ArrayList<Integer> dn4 = new ArrayList<>(Arrays.asList(3,7,8));
    private final ArrayList<Integer> dn5 = new ArrayList<>(Arrays.asList(1,2,6,9,10));
    private final ArrayList<Integer> dn6 = new ArrayList<>(Arrays.asList(1,2,3,7,11,10,9,5));
    private final ArrayList<Integer> dn7 = new ArrayList<>(Arrays.asList(2,3,4,8,12,11,10,6));
    private final ArrayList<Integer> dn8 = new ArrayList<>(Arrays.asList(4,3,7,11,12));
    private final ArrayList<Integer> dn9 = new ArrayList<>(Arrays.asList(5,6,10,14,13));
    private final ArrayList<Integer> dn10 = new ArrayList<>(Arrays.asList(5,6,7,11,15,14,13,9));
    private final ArrayList<Integer> dn11 = new ArrayList<>(Arrays.asList(6,7,8,12,16,15,14,10));
    private final ArrayList<Integer> dn12 = new ArrayList<>(Arrays.asList(7,8,11,15,16));
    private final ArrayList<Integer> dn13 = new ArrayList<>(Arrays.asList(9,10,14));
    private final ArrayList<Integer> dn14 = new ArrayList<>(Arrays.asList(13,9,10,11,15));
    private final ArrayList<Integer> dn15 = new ArrayList<>(Arrays.asList(14,10,11,12,16));
    private final ArrayList<Integer> dn16 = new ArrayList<>(Arrays.asList(15,11,12));


    private final Dice d1 = new Dice(1,d1Array,dn1);
    private final Dice d2 = new Dice(2,d2Array,dn2);
    private final Dice d3 = new Dice(3,d3Array,dn3);
    private final Dice d4 = new Dice(4,d4Array,dn4);
    private final Dice d5 = new Dice(5,d5Array,dn5);
    private final Dice d6 = new Dice(6,d6Array,dn6);
    private final Dice d7 = new Dice(7,d7Array,dn7);
    private final Dice d8 = new Dice(8,d8Array,dn8);
    private final Dice d9 = new Dice(9,d16Array,dn9);
    private final Dice d10 = new Dice(10,d9Array,dn10);
    private final Dice d11 = new Dice(11,d10Array,dn11);
    private final Dice d12 = new Dice(12,d11Array,dn12);
    private final Dice d13 = new Dice(13,d12Array,dn13);
    private final Dice d14 = new Dice(14,d13Array,dn14);
    private final Dice d15 = new Dice(15,d14Array,dn15);
    private final Dice d16 = new Dice(16,d15Array,dn16);


    public Dice[] diceArray = {d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16};

    private String[] diceInPlay = new String[16];
    private ArrayList<String> scoreList = new ArrayList<>();


    public String[] genLetters(){
        String[] diceInPlay = new String[16];
        for(int i = 0; i<16; i++){
          String temp = diceArray[i].genLetter();
          diceInPlay[i]=temp;
        }
        this.diceInPlay = diceInPlay;
        return diceInPlay;

    }

    public ArrayList<String> getDiceInPlay(){

       return new ArrayList<>(Arrays.asList(diceInPlay));

    }
    public Dice getDiceFromDiceArray(int diceNum){
        return diceArray[diceNum-1];
    }


    public void clearScoreList(){
        scoreList.clear();
    }

    public void addToScoreList(String s){
        scoreList.add(s);
    }
    public boolean inScoreList(String s){
        for(String str : scoreList){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }


    public int scoreGame() {
        int score = 0;

        for(String s : scoreList){

            if(s.length()>2) {

               switch(s.length()){
                   case 3:
                   case 4:
                       score = 1;
                       break;
                   case 5:
                       score = 2;
                       break;
                   case 6:
                       score = 3;
                       break;
                   case 7:
                       score = 4;
                       break;
                   default:
                       score = 11;
               }
            }
        }

        return score;
    }

    

}

