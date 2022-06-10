package logic.game;

import logic.player.Bot;
import logic.player.MyPlayer;
import logic.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private MyPlayer myPlayer;
    private GameDeck gameDeck;
    private List<Player> players;
    private State gameState;
    private int round = 1;
    private int numberOfBots;
    private boolean stateIsChanged;

    public Game(MyPlayer myPlayer, int numberOfBots) {
        this.myPlayer = myPlayer;
        myPlayer.setNumberOfHearts(numberOfBots);
        myPlayer.setNumberOfCards(round);
        this.gameDeck = new GameDeck();
        this.players = new ArrayList<>();
        this.players.add(0 , myPlayer);
        this.numberOfBots = numberOfBots;
        runBots();
    }

    protected void runBots () {
        for (int i=1 ; i<= numberOfBots ; i++){
            //tODO : if different bots needed we will add it here.
            Bot bot = new Bot();
            new Thread(bot).start();
            bot.setNumberOfHearts(numberOfBots);
            bot.setNumberOfCards(round);
            this.players.add(i , bot);
        }
    }

    public boolean isStateIsChanged() {
        return stateIsChanged;
    }

    //game.play()
    //game.playRound()
    //game.nextRound()
   public State getState(){
        gameState = new State();
        gameState.setRealPlayerHand(myPlayer.getHand());
        gameState.setDownCards(gameDeck.getDownCards());
        gameState.getNumberOfHearts().put(myPlayer,myPlayer.getNumberOfHearts());
        for (int i = 1; i < players.size(); i++) {
           gameState.getNumberOfCards().put(players.get(i),players.get(i).getNumberOfCards());
           gameState.getNumberOfHearts().put(players.get(i),players.get(i).getNumberOfHearts());
        }
        return gameState;
   }

}
