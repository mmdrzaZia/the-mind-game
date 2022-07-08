package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class LandingPageController extends Controller{
    LandingPage page;
    public LandingPageController(PrintWriter printWriter, BufferedReader bufferedReader, String token) {
        super(printWriter, bufferedReader, token);
    }
    public void setName(String name){
        sendMessageToServer("SET_NAME-"+name);
    }

    public void setPage(LandingPage page) {
        this.page = page;
    }
}
