package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class GameController extends Controller implements Runnable{
    private String gameState;
    private String[] myHand;
    private int player1handSize;
    private int player2handSize;
    private int player3handSize;
    private String[] deck;
    private int gameSize;

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

    //STATE-[]-[]-[]-[]-[]
    private void readState(){
        String[] data = gameState.split("-");
        // [100c50c10c2c0] ==> [100 50 10 2 0]
        deck = data[1].split("c");
        myHand = data[2].split("c");
        player1handSize = Integer.getInteger(data[3]);
        player2handSize = Integer.getInteger(data[4]);
        player3handSize = Integer.getInteger(data[5]);
        System.out.println(deck);
        System.out.println(myHand);
    }

    public void makeMove(String move){
        sendMessageToServer(move);
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    @Override
    public void run() {

        while (true){
            try {
                command = bufferedReader.readLine();
                readData(command);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
