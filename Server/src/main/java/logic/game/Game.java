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
    public Game(MyPlayer myPlayer, int numberOfBots) {
        this.myPlayer = myPlayer;
        this.gameDeck = new GameDeck();
        this.players = new ArrayList<>();
        this.players.set(0 , myPlayer);
        for (int i=1 ; i<= numberOfBots ; i++){
            //tODO : if different bots needed we will add it here.
            this.players.set(i , new Bot());
        }
    }

    //game.play()
    //game.playRound()
    //game.nextRound()
//    public State getState(){
//
//    }

}
