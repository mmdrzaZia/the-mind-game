package logic.game;

import logic.player.Bot;
import logic.player.MyPlayer;
import logic.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game{
    private MyPlayer host;
    private GameDeck gameDeck;
    private List<Player> players;
    private State gameState;
    private GameStatus status;
    private int gameSize;
    private int round;
    private int hearts;

    private Player playerWhoPlayedLastTime;

    public Game(MyPlayer host, int gameSize) {

        this.host = host;

        this.players = new ArrayList<>();
        this.players.add(0 , host);
        this.gameSize = gameSize;
        this.status = GameStatus.WAITING;
    }

    public void initialize(){
        this.status = GameStatus.PAUSED;
        this.gameDeck = new GameDeck();
        this.round = 1;
        this.hearts = gameSize;

        if (players.size() != gameSize){
            for (int i=0 ; i < gameSize-players.size();i++){
                players.add(new Bot());
            }
        }
        gameDeck.dealHand(players , round , gameSize);
    }




    public Player getPlayerWhoPlayedLastTime() {
        return playerWhoPlayedLastTime;
    }

    public void setPlayerWhoPlayedLastTime(Player playerWhoPlayedLastTime) {
        this.playerWhoPlayedLastTime = playerWhoPlayedLastTime;
    }






    public void play(String move){
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
                hearts++;
            } else if (round == 2 || round == 5 || round == 8) {
                giveStarCardToPlayers();
            }
        }
    }



    public void giveStarCardToPlayers () {
        gameDeck.setNumberOfStarCards(gameDeck.getNumberOfStarCards()+1);
    }

    //game.playRound()

   public State getState(MyPlayer player){
        gameState = new State(this , player);
        return gameState;
   }


    public GameStatus getStatus() {
        return status;
    }

    public void makeMove (String move) { // put a card on downCards
        for (int i = 0; i < playerWhoPlayedLastTime.getHand().size(); i++) {
            if (playerWhoPlayedLastTime.getHand().get(i).getNumber() == Integer.parseInt(move)) {
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
                if (players.get(i).getHand().get(j).getNumber() < lastCard.getNumber()) {
                    resultOfMove = false;
                    players.get(i).getHand().remove(j);
                    players.get(i).setNumberOfCards(players.get(i).getNumberOfCards()-1);
                    continue mainFor;
                }
            }
        }
        if (!resultOfMove) {
            hearts--;
        }
    }



    public MyPlayer getHost() {
        return host;
    }

    public GameDeck getGameDeck() {
        return gameDeck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public State getGameState() {
        return gameState;
    }

    public int getGameSize() {
        return gameSize;
    }

    public int getRound() {
        return round;
    }

    public int getHearts() {return hearts;}

    public void setHearts(int hearts) {this.hearts = hearts;}
}
