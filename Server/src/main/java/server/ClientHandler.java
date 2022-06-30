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
    //the games hashmap contains number of players in game and the game itself.
    private static LinkedHashMap<Game , Integer> games = new LinkedHashMap<>();
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
                if (command !=null) {
                    System.out.println("client[" + id + "]: " + command);
                    readData(command);
                }
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
        String message = gson.toJson(game.getState(player));
        sendMessage("state :" + message);
    }


    public void createNewGame(){
        //todo
        game = new Game(player , gameSize);
        games.put(game , 1);
        System.out.println("game is not null now!");
        System.out.println(game);
    }

    public void joinGame(int hostId){
        Game game = getGameByHostId(hostId);
        game.getPlayers().add(player);
        games.replace(game , games.get(game)+1);
        sendMessage("JOINED_GAME-"+games.get(game)+"-"+game.getGameSize());
    }



    public void sendMessage(String message){
        printWriter.println(message);
        printWriter.flush();
    }

    private static void addToClientHandlers(ClientHandler handler){
        clientHandlers.add(handler.getId() ,handler);
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
                player = new MyPlayer(data[1] , this);
                System.out.println("client["+id+"] : name set to " + data[1]);
                break;
            case "CREATE_GAME":
                gameSize = Integer.parseInt(data[1]);
                createNewGame();
                System.out.println("size of client["+id+"]'s game set to "+ gameSize);
                break;
            case "JOIN_GAME":
                joinGame(Integer.parseInt(data[1]));
                System.out.println("client["+id+"] joined client["+data[1]+"]'s game." );
                break;
            case "START_GAME":
                startGame();
                System.out.println("client["+id+"]s game initialized");
                break;
//            case "SEND_WAITING_STAT":
//                sendMessage("JOINED_GAME-"+games.get(game)+"-"+game.getGameSize());
//                System.out.println("client["+id+"] wants waiting stats on game.");
//                break;
        }
    }

    private void startGame() {
        game.initialize();
        sendMessage("GAME_INITIALIZED-"+game.getGameSize());
        sendMessage(game.getState(player).toString());
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
            games += "/"+map.getKey().getHost().getHandler().getId() + "-" + map.getKey().getGameSize()+ "-" + map.getValue();
        }
        if (games.length() != 0)
        games = games.substring(1);
        sendMessage(games);
    }

    private static void messageToAnotherClient(int id,String msg){
        try {
            clientHandlers.get(id).sendMessage(msg);
        }catch (Exception e){
            System.out.println("Error in messageToAnotherClient/ClientHandler.Java");
        }
    }

    public int getId() {
        return id;
    }

    private Game getGameByHostId(int hostId){
        for (Game game : games.keySet()){
            if (game.getHost().getHandler().getId() == hostId){
                return game;
            }
        }
        return null;
    }
}
