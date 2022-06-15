package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class WaitingPageController extends Controller{

    public WaitingPageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }
    public String getGameStatus(){
        /*todo : when logic was complete:
        while (true){
            try {
                String comm = bufferedReader.readLine();
                if (comm !=null && comm.equals("GAME_STARTED")) {
                    break;
                }
                else if (comm != null){
                    return comm;
                }
            }catch (Exception exception){
                System.out.println("Error in WaitingPageController");
            }
        }*/

        return "3/4";
    }
    public void startGame(){
        //todo
        sendMessageToServer("START_GAME");
    }
}
