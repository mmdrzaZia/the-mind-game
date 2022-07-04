package graphic;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JoinOrCreatePageController extends Controller{
    private JoinOrCreatePage frame;
    private int gameSize;

    public JoinOrCreatePageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }


    //Takes the current Games from server.
    //tODO : server : getCurrent Games!
    public List<String> getCurrentGames(){
        try {
            sendMessageToServer("SEND_ME_WAITING_GAMES");
            String gamesStr = bufferedReader.readLine();
            System.out.println("Waiting games : "+gamesStr);
            if (gamesStr !=null) {
                String[] games = gamesStr.split("/");
                return Arrays.asList(games);
            }
        }catch (Exception e){
            System.out.println("Error in getCurrentGames --Join or Create Controller");
        }
        return null;
    }

    public void createGame(Integer size){
       sendMessageToServer("CREATE_GAME-" + (size));
    }

    public void joinGame(int hostId){
        //this.done();
        frame.dispose();
        sendMessageToServer("JOIN_GAME-"+hostId);
        try {
            command = bufferedReader.readLine();
            String[] data = command.split("-");
            if (data[0].equals("JOINED")){
                gameSize = Integer.parseInt(data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (frame.isShowing()){
            process(getCurrentGames());
            Thread.sleep(5000);
        }
        return null;
    }

    @Override
    protected void process(List chunks) {
        frame.getMainPanel().removeAll();
        for (String game : getCurrentGames()){
            String[] gameDetails = game.split("-");
            frame.getMainPanel().add(Box.createRigidArea(new Dimension(10 , 5)));
            if (!Objects.equals(gameDetails[0], "")) {
                JoinGamePanel joinGamePanel = new JoinGamePanel(gameDetails[0], gameDetails[1], gameDetails[2], this);
                frame.getMainPanel().add(joinGamePanel);
            }
            frame.repaint();
            frame.revalidate();
        }
    }

    public void setFrame(JoinOrCreatePage frame) {
        this.frame = frame;
    }

    public int getGameSize() {
        return gameSize;
    }
}
