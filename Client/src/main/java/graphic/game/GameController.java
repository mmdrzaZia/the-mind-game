package graphic.game;

import graphic.Controller;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class GameController extends Controller {
    private String gameState;
    private String[] myHand;
    private int player1handSize;
    private int player2handSize;
    private int player3handSize;
    private String[] deck;
    private int gameSize;
    private int hearts;
    private String gameStatus;
    private int stars;
    private int round;
    private boolean isHost;
    private GameFrame gameFrame;


    public GameController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }

    @Override
    public void readData(String command) {
        String[] data = command.split("-");
        switch (data[0]){
            case "STATE":
                gameState = command;
                readState();
                break;
        }
    }

    //STATE-[]-[]-[]-[]-[]-[]
    private void readState(){
        String[] data = gameState.split("-");
        gameStatus = data[1];
        deck = data[2].split(",");
        myHand = data[3].split(",");
        hearts = Integer.parseInt(data[4]);
        stars = Integer.parseInt(data[5]);
        round = Integer.parseInt(data[6]);
        switch (gameSize){
            case 2:
                player1handSize = Integer.parseInt(data[7]);
                break;
            case 3:
                player1handSize = Integer.parseInt(data[7]);
                player2handSize = Integer.parseInt(data[8]);
                break;
            case 4:
                player1handSize = Integer.parseInt(data[7]);
                player2handSize = Integer.parseInt(data[8]);
                player3handSize = Integer.parseInt(data[9]);
                break;
        }
        updateFrame();
    }

    private void updateFrame() {
        gameFrame.setDeckPanel(new GameDeckPanel(this));
        gameFrame.setHandsPanels(new PlayerHandPanel[gameSize]);
        gameFrame.setupTheGame();
    }

    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            Thread.sleep(1000);
            sendMessageToServer("STATE");
            command = bufferedReader.readLine();
            System.out.println("$SERVER$ : " + command);
            publish(command);
        }
    }

    @Override
    protected void process(List chunks) {
        readData((String) chunks.get(chunks.size()-1));
    }

    public void makeMove(String move){
        //MOVE-NUMBER-2
        if (Objects.equals(gameStatus, "RUNNING"))
            sendMessageToServer("MOVE-"+move);
        else {
            JOptionPane.showMessageDialog(null , "Game is paused");
        }
    }

    public void playRound(){
        sendMessageToServer("PLAY_ROUND");
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    public int getGameSize() {
        return gameSize;
    }

    public int getPlayer1handSize() {
        return player1handSize;
    }

    public int getPlayer2handSize() {
        return player2handSize;
    }

    public int getPlayer3handSize() {
        return player3handSize;
    }

    public String[] getDeck() {
        return deck;
    }

    public String[] getMyHand() {
        return myHand;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public int getHearts() {
        return hearts;
    }

    public int getStars() {
        return stars;
    }

    public int getRound() {
        return round;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public String getGameState() {
        return gameState;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public boolean isHost() {
        return isHost;
    }
}
