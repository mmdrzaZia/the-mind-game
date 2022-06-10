package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerStatus status;
    private ServerSocket serverSocket;

    //
    Server(){
        status = ServerStatus.WAITING;
    }

    /*initializing the Server, and putting the socket port on 8000
     waiting for clients to join.
    */
    public void initialize(){
        System.out.println("Server is " + status);
        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            while (!serverSocket.isClosed()){
                //Server socket will be closed in Client Handler.
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                // The start method begins the execution of a thread.
                thread.start();;
            }
        }catch (IOException e){
            //TODO : either the stack trace or close server is enough we can delete it later on.
            e.printStackTrace();
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
// if Server is listening for new Clients : Waiting
// if Server is playing the Game : Running
enum ServerStatus {
    WAITING , RUNNING;
}
