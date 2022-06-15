package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class LandingPageController extends Controller{

    public LandingPageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }
    public void setName(String name){
        sendMessageToServer("SET_NAME-"+name);
    }
}
