package logic.game;

import logic.player.MyPlayer;
import logic.player.Player;

import java.util.*;

public class State {
    private Game game;
    private ArrayList<Card> hand;
    private Stack<Card> downCards;
    private LinkedHashMap<Player,Integer> numberOfCards;
    private int gameSize;


    public State(Game game, MyPlayer player) {
        this.game = game;
        gameSize = game.getGameSize();
        hand = player.getHand();
        downCards = game.getGameDeck().getDownCards();
        numberOfCards = new LinkedHashMap<>();
        for (Player p : game.getPlayers()){
            numberOfCards.put(p,p.getHand().size());
        }
    }
    //todo
    @Override
    public String toString() {
        Set<Player> keySet = numberOfCards.keySet();
        Player[] keyArray
                = keySet.toArray(new Player[keySet.size()-1]);

        String down = downCards.toString().substring(1,downCards.toString().length()-1);
        String handString = hand.toString().substring(1,hand.toString().length()-1);

        //STATE-[STATUS]-[DOWN_CARDS]-[MY_PLAYER_HAND]-[HEARTS]-[STARS]-[ROUND]
        switch (gameSize){
            case 2:
                return "STATE-"+game.getStatus()+"-"+down+"-"+handString+"-"+game.getHearts()+"-"+game.getStars()+"-"+game.getRound()+"-"+numberOfCards.get(keyArray[0]);
            case 3:
                return "STATE-"+game.getStatus()+"-"+down+"-"+handString+"-"+game.getHearts()+"-"+game.getStars()+"-"+game.getRound()+"-"+numberOfCards.get(keyArray[0])+"-"+numberOfCards.get(keyArray[1]);
            case 4:
                return "STATE-"+game.getStatus()+"-"+down+"-"+handString+"-"+game.getHearts()+"-"+game.getStars()+"-"+game.getRound()+"-"+numberOfCards.get(keyArray[0])+"-"+numberOfCards.get(keyArray[1])+"-"+numberOfCards.get(keyArray[2]);
            default:
                System.out.println("GAME SIZE IS NOT RIGHT!!!!!");
                return null;
        }

    }


//    public void setDownCards(Stack<Card> downCards) {
//        this.downCards = downCards;
//    }
//
//
//    public HashMap<Player, Integer> getNumberOfCards() {
//        return numberOfCards;
//    }
}
