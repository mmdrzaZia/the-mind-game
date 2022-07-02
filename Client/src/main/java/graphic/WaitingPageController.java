package graphic;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

public class WaitingPageController extends Controller{
    private boolean startPressed = false;
    private int gameSize;
    private JFrame frame;

    public WaitingPageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);

    }
    public void startGame(){
        sendMessageToServer("START_GAME");
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

    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            command = bufferedReader.readLine();
            publish(command);
        }
    }

    @Override
    protected void process(List chunks) {
        readData(command);
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
