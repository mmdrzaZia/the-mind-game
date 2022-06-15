package logic.player;

import logic.game.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected ArrayList<Card> hand;
    protected int numberOfHearts;
    protected int numberOfCards;
    protected boolean isAlive;
    protected String name;

    public void setNumberOfHearts (int numberOfPlayers) {
        numberOfHearts = numberOfPlayers;
    }

    public void setNumberOfCards (int round) {
        numberOfCards = round;
    }

    public int getNumberOfHearts() {
        return numberOfHearts;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isAlive() {
        return isAlive;
    }

    //playStrategy?????

    //checkIfAlive()
}
