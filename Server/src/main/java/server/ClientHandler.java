package server;

import logic.game.Game;
import logic.game.GameStatus;
import logic.player.MyPlayer;
import java.io.*;
import java.net.Socket;
import com.google.gson.Gson;

public class ClientHandler implements Runnable{
    // Socket for a connection, buffer reader and writer for receiving and sending data respectively.
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Game game;

    public ClientHandler(Socket socket) {
        System.out.println("new client handler created.");
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream());
            //when a client connects their username is sent.
        }catch (IOException e){
            e.printStackTrace();
            //tODO : should write a function that close everything.
        }
    }
    @Override
    public void run() {
        while (socket.isConnected()){
            try {
                String command = bufferedReader.readLine();
                if (command != null) {
                    System.out.println(command);
                }
            }
            catch (Exception e){
            }
        }
    }

    public void sendState(){
        Gson gson = new Gson();
        String message = gson.toJson(game.getState());
        sendMessage("state :" + message);
    }


    public void sendMessage(String message){
        printWriter.println(message);
        printWriter.flush();
    }
}
