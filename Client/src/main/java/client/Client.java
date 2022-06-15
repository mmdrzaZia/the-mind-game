package client;

import graphic.LandingPage;
import graphic.LandingPageController;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Client(){
        init();
    }

    private void init() {
        try {
            socket = new Socket("localhost" , 8080);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            LandingPageController landingPageController = new LandingPageController(printWriter,bufferedReader);
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
