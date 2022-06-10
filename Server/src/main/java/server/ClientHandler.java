package server;

import logic.game.Game;
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
        if (wantsToPlayGame()){
            MyPlayer myPlayer = new MyPlayer();
            game = new Game(myPlayer , numberOfBots());
            sendMessage("Game Created");
            if (wantsToStartTheGame()){
                game.play();
            }
        }
    }

    public void sendState(){
        Gson gson = new Gson();
        String message = gson.toJson(game.getState());
        sendMessage("state :" + message);
    }

    private boolean wantsToStartTheGame() {
        try {
            sendMessage("Do you want to start?(y/n)");
            String res = bufferedReader.readLine();
            System.out.println("client: " + res);
            if (res.equals("y")) {
                return true;
            }else {
                return wantsToPlayGame();
            }
        }catch (IOException e){
            e.printStackTrace();
        }return wantsToPlayGame();
    }

    private boolean wantsToPlayGame(){
        try {
            sendMessage("Do you want to play the mind-game?(y/n)");
            String res = bufferedReader.readLine();
            System.out.println("client " + res);
            if (res.equals("y")) {
                return true;
            }else {
                return wantsToPlayGame();
            }
        }catch (IOException e){
            e.printStackTrace();
        }return wantsToPlayGame();
    }
    private int numberOfBots(){
        sendMessage("How many bots you want to add?");
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfBots();
    }

    public void sendMessage(String message){
        printWriter.println(message);
        printWriter.flush();
    }
}
