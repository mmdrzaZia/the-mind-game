package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class WaitingPageController extends Controller{
    private boolean startPressed = false;
    private int gameSize;

    public WaitingPageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }
    public void startGame(){
        sendMessageToServer("START_GAME");
        while (true){
            try {
                command = bufferedReader.readLine();
                readData(command);
                break;
            }catch (Exception e){

            }
        }
    }

    @Override
    public void readData(String command) {
        String[] data = command.split("-");
        switch (data[0]){
            case "GAME_INITIALIZED":
                gameSize = Integer.parseInt(data[1]);
                break;
        }
    }

    public void setStartPressed(boolean startPressed) {
        this.startPressed = startPressed;
    }

    public boolean isStartPressed() {
        return startPressed;
    }



    public String getCommand() {
        return command;
    }

    public int getGameSize() {
        return gameSize;
    }
}
