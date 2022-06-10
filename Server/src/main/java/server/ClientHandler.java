package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable{
    // Socket for a connection, buffer reader and writer for receiving and sending data respectively.
    private Socket socket;
    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.inputStreamReader = new InputStreamReader(socket.getInputStream());
            this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            //when a client connects their username is sent.
            outputStreamWriter.write("Enter username : ");
            this.clientUsername = new BufferedReader(inputStreamReader).readLine();


        }catch (IOException e){
            e.printStackTrace();
            //tODO : should write a function that close everything.
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()){
            try {
                System.out.println(new BufferedReader(inputStreamReader).readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
