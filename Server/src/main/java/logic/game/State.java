package logic.game;

import logic.player.MyPlayer;
import logic.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class State {
    private ArrayList<Card> hand;
    private Stack<Card> downCards;
    private HashMap<Player,Integer> numberOfCards;


    public State(Game game, MyPlayer player) {
        hand = player.getHand();
        downCards = game.getGameDeck().getDownCards();
        numberOfCards = new HashMap<>();
        for (Player p : game.getPlayers()){
            numberOfCards.put(p,p.getNumberOfCards());
        }
    }
    //todo
    @Override
    public String toString() {
        return "STATE-["+downCards+"]-["+hand+"]-["+numberOfCards.get(1)+"]-["+numberOfCards.get(2)+"]-["+numberOfCards.get(3)+"]";
    }

    private String handString(){
        StringBuilder hand = new StringBuilder();
        for (Card card : this.hand){
            hand.append(card.toString()).append("c");
        }
        hand.deleteCharAt(hand.length());
        return hand.toString();
    }
    private String downCardsString(){
        StringBuilder down = new StringBuilder();
        for (Card card : downCards){
            down.append(card.toString()).append('c');
        }
        return down.toString();
    }


    public void setDownCards(Stack<Card> downCards) {
        this.downCards = downCards;
    }


    public HashMap<Player, Integer> getNumberOfCards() {
        return numberOfCards;
    }
}
