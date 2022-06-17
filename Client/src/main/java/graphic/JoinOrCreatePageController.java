package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class JoinOrCreatePageController extends Controller{

    public JoinOrCreatePageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }


    //Takes the current Games from server.
    //tODO : server : getCurrent Games!
    public List<String> getCurrentGames(){
        // { host:"" , gameSize: , playerNeeded:  }
        try {
            sendMessageToServer("SEND_ME_WAITING_GAMES");
            String gamesStr = bufferedReader.readLine();

            gamesStr = "host-4-3/15-5-4/16-7-5/18-8-5/19-15-10";
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
       sendMessageToServer("CREATE_GAME-" + (size+1));
    }
}
