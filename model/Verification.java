package model;

public class Verification {

    private Dictionary dict = new Dictionary();

    public boolean verifyNeighbor(Dice lastDice, Dice newDice) {

        return lastDice.getNeighbors().contains(newDice.getDiceNum());
    }

    public boolean inDictionary(String s){

        return dict.checkDictionary(s);
    }
}
