package client;

import graphic.*;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;


    private void init() {
        try {
            socket = new Socket("localhost" , 8080);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            LandingPageController landingPageController = new LandingPageController(printWriter , bufferedReader);
            LandingPage landingPage = new LandingPage(landingPageController);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
    }
}
