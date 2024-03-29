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
            for (int i=0 ; i < gameSize+2-players.size();i++){
                players.add(new Bot(this));
            }
        }
    }


    public void playStar (){
        leastNumbers = new ArrayList<>();
        for (int z = 0; z < players.size(); z++) {
            leastNumbers.add(players.get(z).getLowestCard().getNumber());
            players.get(z).getHand().remove(players.get(z).getLowestCard());

        }
        stars--;
        nextRound();
    }

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
            }
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
            if (players.get(i) instanceof Bot) {
                Thread thread = new Thread((Runnable) players.get(i));
                botThreads.add(thread);
                thread.start();
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
            }
        }
        nextRound();
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

    public int getGameSize() {
        return gameSize;
    }

    public int getRound() {
        return round;
    }

    public int getHearts() {return hearts;}

    public int getStars() {
        return stars;
    }

    public String getLowestCards(){
        String cards = "";
        for (Player player : players){
            cards += player.getLowestCard() +",";
        }
        return cards.substring(0,cards.length()-1);
    }
}
