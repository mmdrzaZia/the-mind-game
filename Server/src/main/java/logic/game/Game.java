package logic.game;

import logic.player.Bot;
import logic.player.MyPlayer;
import logic.player.Player;
import server.ClientHandler;

import java.util.ArrayList;
import java.util.List;

public class Game{
    private MyPlayer myPlayer;
    private GameDeck gameDeck;
    private List<Player> players;
    private State gameState;
    private GameStatus status;
    private int round;
    private int numberOfBots;
    private boolean stateIsChanged;
    private ClientHandler clientHandler;
    private Player playerWhoPlayedLastTime;

    public Game(MyPlayer myPlayer, int numberOfBots, ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.myPlayer = myPlayer;
        myPlayer.setNumberOfHearts(numberOfBots);
        myPlayer.setNumberOfCards(round);
        this.gameDeck = new GameDeck();
        this.players = new ArrayList<>();
        this.players.add(0 , myPlayer);
        this.numberOfBots = numberOfBots;
        this.status = GameStatus.PAUSED;
        this.stateIsChanged = true; // must be false
        round = 1;
    }

    public Player getPlayerWhoPlayedLastTime() {
        return playerWhoPlayedLastTime;
    }

    public void setPlayerWhoPlayedLastTime(Player playerWhoPlayedLastTime) {
        this.playerWhoPlayedLastTime = playerWhoPlayedLastTime;
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



    public void play(String move){
        this.status = GameStatus.RUNNING;
        while (round < 13 && alivePlayerExists()){
            makeMove(move);
            nextRound();
            /*if (playRound()){
                nextRound();
            }*/
        }
        status = GameStatus.FINISHED;
    }

    private boolean alivePlayerExists() {
        for (Player player : players){
            if (player.isAlive()) return true;
        }
        return false;
    }

    /*private boolean playRound(){
        gameDeck.getPlayersCards();
        gameDeck.dealCard(round , players);

    }*/

    private void nextRound(){
        boolean canGoToNextRound = true;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHand().size() != 0) {
                canGoToNextRound = false;
            }
        }
        if (canGoToNextRound) {
            round++;
            if (round == 3 || round == 6 || round == 9) {
                giveHeartCardToPlayers();
            } else if (round == 2 || round == 5 || round == 8) {
                giveStarCardToPlayers();
            }
        }
    }

    public void giveHeartCardToPlayers () {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setNumberOfHearts(players.get(i).getNumberOfHearts() + 1);
        }
    }

    public void giveStarCardToPlayers () {
        gameDeck.setNumberOfStarCards(gameDeck.getNumberOfStarCards()+1);
    }

    //game.playRound()

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

    public void setStateIsChanged(boolean stateIsChanged) {
        this.stateIsChanged = stateIsChanged;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void makeMove (String move) { // put a card on downCards
        for (int i = 0; i < playerWhoPlayedLastTime.getHand().size(); i++) {
            if (playerWhoPlayedLastTime.getHand().get(i).getId() == Integer.parseInt(move)) {
                gameDeck.getDownCards().push(playerWhoPlayedLastTime.getHand().get(i));
                playerWhoPlayedLastTime.getHand().remove(i);
                playerWhoPlayedLastTime.setNumberOfCards(playerWhoPlayedLastTime.getNumberOfCards()-1);
                break;
            }
        }
        checkResultOfMove();
    }

    public void checkResultOfMove () { // check that the move is correct or not
        boolean resultOfMove = true;
        Card lastCard = gameDeck.getDownCards().peek();
        mainFor :
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.get(i).getHand().size(); j++) {
                if (players.get(i).getHand().get(j).getId() < lastCard.getId()) {
                    resultOfMove = false;
                    players.get(i).getHand().remove(j);
                    players.get(i).setNumberOfCards(players.get(i).getNumberOfCards()-1);
                    continue mainFor;
                }
            }
        }
        if (!resultOfMove) {
            playerWhoPlayedLastTime.setNumberOfHearts(playerWhoPlayedLastTime.getNumberOfHearts()-1);
            deadPlayer(playerWhoPlayedLastTime);
        }
    }

    public void deadPlayer (Player playerWhoPlayedLastTime) {
        if (playerWhoPlayedLastTime.getNumberOfHearts() == 0) {
            players.remove(playerWhoPlayedLastTime);
        }
    }
}
