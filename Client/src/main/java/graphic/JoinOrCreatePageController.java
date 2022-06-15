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
            //String gamesStr = bufferedReader.readLine();
            String gamesStr = "host-4-3/15-5-4/16-7-5";
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
       sendMessageToServer("CREATED_GAME-"+size);
    }

}
