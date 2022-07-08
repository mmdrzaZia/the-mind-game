package client;

import graphic.LandingPage;
import graphic.LandingPageController;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String token;


    private void init() {
        try {
            socket = new Socket("localhost" , 8080);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            token = bufferedReader.readLine();
            LandingPageController landingPageController = new LandingPageController(printWriter,bufferedReader, token);
            LandingPage landingPage = new LandingPage(landingPageController);
        }catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null , "Server is Down, try later..." , "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void run() {
        init();
    }
}
