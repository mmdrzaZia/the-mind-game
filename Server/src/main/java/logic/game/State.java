package logic.game;

import logic.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class State {
    private ArrayList<Card> realPlayerHand;
    private Stack<Card> downCards;
    private HashMap<Player,Integer> numberOfHearts;
    private HashMap<Player,Integer> numberOfCards;

    public State() {
        realPlayerHand = new ArrayList<>();
        downCards = new Stack<>();
        numberOfHearts = new HashMap<>();
        numberOfCards = new HashMap<>();
    }

    public void setRealPlayerHand(ArrayList<Card> realPlayerHand) {
        this.realPlayerHand = realPlayerHand;
    }

    public void setDownCards(Stack<Card> downCards) {
        this.downCards = downCards;
    }

    public void setNumberOfHearts(HashMap<Player, Integer> numberOfHearts) {
        this.numberOfHearts = numberOfHearts;
    }

    public void setNumberOfCards(HashMap<Player, Integer> numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public HashMap<Player, Integer> getNumberOfHearts() {
        return numberOfHearts;
    }

    public HashMap<Player, Integer> getNumberOfCards() {
        return numberOfCards;
    }
}
