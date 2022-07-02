package graphic;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GameController extends Controller{
    private String gameState;
    private String[] myHand;
    private int player1handSize;
    private int player2handSize;
    private int player3handSize;
    private String[] deck;
    private int gameSize;
    private int hearts;
    private GameFrame gameFrame;



    public GameController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }


    @Override
    public void readData(String command) {
        System.out.println("State from server : "+ command);
        String[] data = command.split("-");
        switch (data[0]){
            case "STATE":
                gameState = command;
                readState();
                break;
        }
    }

    //STATE-[]-[]-[]-[]-[]
    private void readState(){
        String[] data = gameState.split("-");
        // [100c50c10c2c0] ==> [100 50 10 2 0]
        deck = data[1].split(",");
        myHand = data[2].split(",");
        hearts = Integer.parseInt(data[3]);
        switch (gameSize){
            case 2:
                player1handSize = Integer.getInteger(data[4]);
                break;
            case 3:
                player1handSize = Integer.getInteger(data[4]);
                player2handSize = Integer.getInteger(data[5]);
                break;
            case 4:
                player1handSize = Integer.getInteger(data[4]);
                player2handSize = Integer.getInteger(data[5]);
                player3handSize = Integer.getInteger(data[6]);
                break;
        }
        updateFrame();
    }

    private void updateFrame() {
        gameFrame.setDeckPanel(new GameDeckPanel(this));
        gameFrame.setHandsPanels(new PlayerHandPanel[gameSize]);
        gameFrame.setupTheGame();
    }

    public void makeMove(String move){
        sendMessageToServer(move);
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
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

    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            command = bufferedReader.readLine();
            publish(command);
        }
    }

    @Override
    protected void process(List chunks) {
        readData((String) chunks.get(chunks.size()-1));
    }

    public int getHearts() {
        return hearts;
    }
}
