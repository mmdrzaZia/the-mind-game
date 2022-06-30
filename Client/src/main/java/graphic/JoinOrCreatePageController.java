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
        sendMessageToServer("JOIN_GAME-"+hostId);
    }
}
