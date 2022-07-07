package logic.player;

import logic.game.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected ArrayList<Card> hand;
    protected int numberOfCards;
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

    public Card getLowestCard() {
        int temp = 100;
        Card lowestCard = null;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getNumber() < temp) {
                temp = hand.get(i).getNumber();
                lowestCard = hand.get(i);
            }
        }
        return lowestCard;
    }

}
