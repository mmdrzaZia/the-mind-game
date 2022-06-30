package logic.player;

import logic.game.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected ArrayList<Card> hand;
    protected int numberOfCards;
    protected boolean isAlive;
    protected String name;

    public Player(){
        hand = new ArrayList<>();
    }

    public void setNumberOfCards (int round) {
        numberOfCards = round;
    }


    public int getNumberOfCards() {
        return numberOfCards;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }


    //playStrategy?????

    //checkIfAlive()
}
