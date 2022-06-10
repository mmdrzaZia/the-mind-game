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

    @Override
    public String toString() {
        return '{' +
                "realPlayerHand=" + realPlayerHand +
                ", downCards=" + downCards +
                ", numberOfHearts=" + numberOfHearts +
                ", numberOfCards=" + numberOfCards +
                '}';
    }

    public void setRealPlayerHand(ArrayList<Card> realPlayerHand) {
        this.realPlayerHand = realPlayerHand;
    }

    public void setDownCards(Stack<Card> downCards) {
        this.downCards = downCards;
    }

    public HashMap<Player, Integer> getNumberOfHearts() {
        return numberOfHearts;
    }

    public HashMap<Player, Integer> getNumberOfCards() {
        return numberOfCards;
    }
}
