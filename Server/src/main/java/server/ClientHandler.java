package server;

import logic.game.Game;
import logic.game.GameStatus;
import logic.player.MyPlayer;

import java.io.*;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;
public class ClientHandler implements Runnable{
    private static List<ClientHandler> clientHandlers = new ArrayList<>();
    private static HashMap<Game , Integer> games = new HashMap<>();
    private Socket socket;
    private static int idCounter = 0;
    private int id;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private MyPlayer player;
    private Game game;
    private int gameSize;


    public ClientHandler(Socket socket) {
        System.out.println("new client handler created.");
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
            //tODO : should write a function that close everything.
        }
        this.id = idCounter;
        addToClientHandlers(this);
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()){
                String command = bufferedReader.readLine();
                if (command !=null)
                System.out.println("client["+id+"]: "+command);
                readData(command);

                if (game!=null && game.getStatus() == GameStatus.RUNNING){
                    sendState();
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


    public void createNewGame(){
        //todo
        Game game = new Game(player , gameSize, this);
        games.put(game , id);
    }
    public void joinGame(int hostId){

    }



    public void sendMessage(String message){
        printWriter.println(message);
        printWriter.flush();
    }

    private static void addToClientHandlers(ClientHandler handler){
        clientHandlers.add(handler);
        idCounter++;
    }

    private void readData(String command){
        if (command == null) return;
        String[] data = command.split("-");
        switch (data[0]){
            case "SEND_ME_WAITING_GAMES":
                sendWaitingGames();
                System.out.println("sent waiting games to client["+id+"]");
                break;
            case "SET_NAME":
                player = new MyPlayer(data[1]);
                System.out.println("client["+id+"] : name set to " + data[1]);
                break;
            case "CREATE_GAME":
                gameSize = Integer.parseInt(data[1]);
                createNewGame();
                System.out.println("size of client["+id+"]'s game set to "+ gameSize);
                break;
        }
    }



    public HashMap<Game,Integer> getWaitingGames(){
        HashMap<Game , Integer> waitings = new HashMap();
        for (Map.Entry<Game , Integer> map : games.entrySet()){
            if (map.getKey().getStatus() == GameStatus.WAITING){
                waitings.put(map.getKey() , map.getValue());
            }
        }
        return waitings;
    }

    public void sendWaitingGames(){
        String games = "";
        for (Map.Entry<Game,Integer> map : getWaitingGames().entrySet()){
            games += map.getKey().toString() + map.getValue() + "-4-3";
        }
        sendMessage(games);
    }

    private static void messageToAnotherClient(int id,String msg){
        try {
            clientHandlers.get(id).sendMessage(msg);
        }catch (Exception e){
            System.out.println("Error in messageToAnotherClient/ClientHandler.Java");
        }
    }
}
