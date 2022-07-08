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
    private int stars = 2;
    private ArrayList<Integer> leastNumbers;
    private List<Thread> botThreads;
    //private boolean move;

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
        botThreads = new ArrayList<>();

        if (players.size() != gameSize){
            for (int i=0 ; i < gameSize-players.size();i++){
                players.add(new Bot(this));
            }
        }
        //gameDeck.dealHand(players , round , gameSize);
        //playRound(true);
    }


    public void playStar (){
        leastNumbers = new ArrayList<>();
        for (int z = 0; z < players.size(); z++) {
            players.get(z).getHand().remove(players.get(z).getLowestCard());
            leastNumbers.add(players.get(z).getLowestCard().getNumber());
        }
        stars--;
    }



    /*private boolean playRound(){
        gameDeck.getPlayersCards();
        gameDeck.dealCard(round , players);
    }*/

    private boolean someoneHasCard(){
        boolean someoneHasCard = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHand().size() != 0) {
                someoneHasCard = true;
            }
        }
        return someoneHasCard;
    }

    public void nextRound(){
        boolean canGoToNextRound = !someoneHasCard();

        if (canGoToNextRound) {
            status = GameStatus.PAUSED;
            round++;
            if (round == 3 || round == 6 || round == 9) {
                hearts++;
            } else if (round == 2 || round == 5 || round == 8) {
                giveStarCardToPlayers();
            } else if (round == 13) {
                status = GameStatus.WIN;
                // send message to client
            }
            //playRound();
        } else {
            continueRound();
        }
    }

    public void giveStarCardToPlayers () {
        stars++;
    }

    public void playRound() {
        gameDeck.dealHand(players, round, gameSize);
        status = GameStatus.RUNNING;
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setNumberOfCards(round);
            // give hand to players
            if (players.get(i) instanceof Bot) {
                Thread thread = new Thread((Runnable) players.get(i));
                botThreads.add(thread);
                thread.start();
            }
        }
        /*while (!move) {}
        for (int j = 0; j < botThreads.size(); j++) {
            if (!botThreads.get(j).isInterrupted()) {
                botThreads.get(j).interrupt();
            }
        }
        move = false;*/
    }

    public void continueRound(){
        for (int i = 0; i < players.size(); i++) {
            // give hand to players
            if (players.get(i) instanceof Bot) {
                Thread thread = new Thread((Runnable) players.get(i));
                thread.run();
                botThreads.add(thread);
            }
        }
    }

   public State getState(MyPlayer player){
        gameState = new State(this , player);
        return gameState;
   }


    public GameStatus getStatus() {
        return status;
    }

    public void makeMove (long cardNumber,Player lastPlayedPlayer) { // put a card on downCards
        for (int i = 0; i < lastPlayedPlayer.getHand().size(); i++) {
            if (lastPlayedPlayer.getHand().get(i).getNumber() == cardNumber) {
                gameDeck.getDownCards().push(lastPlayedPlayer.getHand().get(i));
                lastPlayedPlayer.getHand().remove(i);
                lastPlayedPlayer.setNumberOfCards(lastPlayedPlayer.getNumberOfCards()-1);
                break;
            }
        }
        checkResultOfMove();
    }

    private void checkResultOfMove () { // check that the move is correct or not
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
            if (hearts == 0) {
                status = GameStatus.LOSE;
                // send finish message to client

            }
        }
        for (int i = 0; i < botThreads.size(); i++) {
            if (!botThreads.get(i).isInterrupted()) {
                botThreads.get(i).interrupt();
            }
        }
        nextRound();
    }


    /*public void setMove(boolean move) {
        this.move = move;
    }*/

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

    public int getStars() {
        return stars;
    }

    public String getLowestCards(Player myPlayer){
        String lowest = "";
        for (Player player : players){
            lowest += ("-"+player.getLowestCard());
            lowest.substring(0 , lowest.length()-1);
        }
        return lowest;
    }
}
