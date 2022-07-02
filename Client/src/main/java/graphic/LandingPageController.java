package graphic;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class LandingPageController extends Controller{
    LandingPage page;
    public LandingPageController(PrintWriter printWriter, BufferedReader bufferedReader) {
        super(printWriter, bufferedReader);
    }
    public void setName(String name){
        sendMessageToServer("SET_NAME-"+name);
    }

    public void setPage(LandingPage page) {
        this.page = page;
    }
}
