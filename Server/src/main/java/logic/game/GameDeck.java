package logic.game;

import logic.player.MyPlayer;
import logic.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GameDeck {
    private ArrayList<Card> deck;
    private Stack<Card> downCards;
    //private int numberOfStarCards = 2;

    public GameDeck(){
        setDeck();
        downCards = new Stack<>();
    }

//    public int getNumberOfStarCards() {
//        return numberOfStarCards;
//    }
//
//    public void setNumberOfStarCards(int numberOfStarCards) {
//        this.numberOfStarCards = numberOfStarCards;
//    }

    private void setDeck () {
        deck = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Card card = new Card(i);
            deck.add(card);
        }
        shuffleDeck();
    }

    private void shuffleDeck() {
        Random rand = new Random();

        for (int i = 0; i < deck.size(); i++) {
            int randomIndexToSwap = rand.nextInt(deck.size());
            Card temp = deck.get(randomIndexToSwap);
            deck.set(randomIndexToSwap , deck.get(i));
            deck.set(i , temp);
        }
    }

    public Stack<Card> getDownCards() {
        return downCards;
    }
    public Card dealCardFromTop(){
        Card card = deck.get(deck.size()-1);
        deck.remove(card);
        return card;
    }

//    public void dealHand(List<Player> players, int round) {
//        shuffleDeck();
//        for (Player player : players){
//            for (int i=0 ; i<round ; i++) {
//                player.getHand().add(dealCardFromTop());
//            }
//        }
//    }

    public void dealHand(List<Player> players, int round,int gameSize){
        for (Player player : players){
            for (int i=0 ; i<round ; i++) {
                player.getHand().add(dealCardFromTop());
            }
            if (player instanceof MyPlayer) ((MyPlayer) player).setHasStarCard(true);
        }
    }

//    public void getCardsFromPlayers(List<Player> players){
//        for (Player player : players){
//            deck.addAll(player.getHand());
//            player.getHand().clear();
//        }
//        shuffleDeck();
//    }
}


