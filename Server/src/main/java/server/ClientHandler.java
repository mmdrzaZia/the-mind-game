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
        try {

            if (wantsToPlayGame()) {
                sendMessage("name?");
                MyPlayer myPlayer = new MyPlayer(bufferedReader.readLine());
                game = new Game(myPlayer, numberOfBots());
                sendMessage("Game Created");
                if (wantsToStartTheGame()) {
                    String move;
                    game.play();
                    while (game.getStatus() == GameStatus.RUNNING) {
                        if (game.isStateIsChanged()) {
                            sendMessage("move");
                            //TODO:
                            move = bufferedReader.readLine();
                            System.out.println("client: " + move);
                            if (game.moveIsValid(move)){
                                game.makeMove(move);
                            }else {
                                sendMessage("move was not valid");
                                sendMessage("move");
                            }
                            sendState();
                            game.setStateIsChanged(false);
                        }
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
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
