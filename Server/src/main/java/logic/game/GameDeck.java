package logic.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GameDeck {
    private ArrayList<Card> deck;
    private Stack<Card> downCards;

    public GameDeck(){
        setDeck();
        downCards = new Stack<>();
    }

    private void setDeck () {
        deck = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Card card = new Card(CardType.NUMBER , i);
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
    //private dealCard(int Round , List<Player> alivePlayers)
}


