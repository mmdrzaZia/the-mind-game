package server;

import logic.game.MessageController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    public void initialize(){
        try{
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is Running...");
            while (!serverSocket.isClosed()){
                //Server socket will be closed in Client Handler.
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                MessageController messageController = new MessageController(clientHandler);
                Thread thread = new Thread(clientHandler);
                // The start method begins the execution of a thread.
                thread.start();
            }
        }catch (IOException e){
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
