package server;

import logic.game.Game;
import logic.game.GameStatus;
import logic.player.MyPlayer;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

import com.google.gson.Gson;
import logic.player.Player;

public class ClientHandler implements Runnable{
    private static List<ClientHandler> clientHandlers = new ArrayList<>();
    private static LinkedHashMap<Game , Integer> games = new LinkedHashMap<>();
    private static int idCounter = 0;
    private Socket socket;
    private String token;
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
            token = TokenGenerator.generateToken();
            printWriter.println(token);
            printWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        this.id = idCounter;
        addToClientHandlers(this);
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()){
                String command = bufferedReader.readLine();
                if (command !=null) {
                    System.out.println("client[" + id + "]: " + command);
                    readData(command);
                } if (command == null){
                    socket.close();
                    clientHandlers.remove(this);
                    idCounter--;
                    System.out.println("Client disconnected");
                }
            }
            games.remove(game);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

//    public void sendState(){
//        Gson gson = new Gson();
//        String message = gson.toJson(game.getState(player));
//        sendMessage("state :" + message);
//    }


    public void createNewGame(){
        //todo
        game = new Game(player , gameSize);
        games.put(game , 1);
    }

    public void joinGame(int hostId){
        game = getGameByHostId(hostId);
        game.getPlayers().add(player);
        games.replace(game , games.get(game)+1);

        sendMessageToAllGamePlayers("JOINED-"+games.get(game));
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
            if (!data[0].equals(token)) return;

        switch (data[1]){
            case "SEND_ME_WAITING_GAMES":
                sendWaitingGames();
                System.out.println("sent waiting games to client["+id+"]");
                break;
            case "SET_NAME":
                player = new MyPlayer(data[2] , this);
                System.out.println("client["+id+"] : name set to " + data[1]);
                break;
            case "CREATE_GAME":
                gameSize = Integer.parseInt(data[2]);
                createNewGame();
                System.out.println("size of client["+id+"]'s game set to "+ gameSize);
                break;
            case "JOIN_GAME":
                joinGame(Integer.parseInt(data[2]));
                System.out.println("client["+id+"] joined client["+data[1]+"]'s game." );
                break;
            case "INITIALIZE_GAME":
                initializeGame();
                System.out.println("client["+id+"]s game initialized");
                sendMessageToAllGamePlayers("GAME_INITIALIZED");
                break;
            case "STATE":
                String state = game.getState(player).toString();
                sendMessage(state);
                System.out.println("State sent to client [" + id + "]");
                break;
            case "PLAY_ROUND":
                game.playRound();
                System.out.println("client [" + id + "]s round started. round: " + game.getRound());
                break;
            case "MOVE":
                switch (data[2]){
                    case "STAR":
                        game.playStar();
                        sendMessageToAllGamePlayers("");
                        break;
                    case "NUMBER":
                        if (data[3].charAt(0) == ' ')
                            data[3] = data[3].substring(1);
                        game.makeMove(Long.parseLong(data[3]) , player);
                        break;
                }
                break;
        }
    }

    private void initializeGame() {
        game.initialize();
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
    public void sendMessageToAllGamePlayers(String message){
        for (Player player : game.getPlayers()){
            if (player instanceof MyPlayer){
               ((MyPlayer) player).getHandler().sendMessage(message);
            }
        }
    }
}
