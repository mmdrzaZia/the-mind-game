package graphic;

import graphic.game.GameController;
import graphic.game.GameFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

public class WaitingPageController extends Controller{
    private WaitingPage frame;

    public WaitingPageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);

    }
    public void startGame(){
        sendMessageToServer("INITIALIZE_GAME");
    }


    @Override
    protected Object doInBackground() throws Exception {
        while (true){
            Thread.sleep(2500);
            command = bufferedReader.readLine();
            System.out.println("SERVER : " + command);
            if (command!= null && command.split("-")[0].equals("JOINED"))
                publish(Integer.parseInt(command.split("-")[1]));
            if (command != null && command.split("-")[0].equals("GAME_INITIALIZED")){
                frame.dispose();
                break;
            }
        }
        GameController gameController = new GameController(this.getPrintWriter(), this.getBufferedReader());
        gameController.setGameSize(frame.getGameSize());
        GameFrame gameFrame = new GameFrame(frame.getGameSize(),gameController , true);
        return null;
    }
    @Override
    protected void process(List chunks) {
        frame.getPlayersJoinedLabel().setText(chunks.get(chunks.size()-1)+" players joined!");
    }





    public String getCommand() {
        return command;
    }


    public void setFrame(WaitingPage frame) {
        this.frame = frame;
    }
}
