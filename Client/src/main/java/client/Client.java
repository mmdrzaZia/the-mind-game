package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Client(){
        init();
    }

    private void init() {
        try {
            socket = new Socket("localhost" , 8080);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            while (socket.isConnected()){
                String message = bufferedReader.readLine();
                System.out.println(message);
                switch (message){
                    case "Do you want to play the mind-game?(y/n)":
                        printWriter.println(new Scanner(System.in).nextLine());
                        printWriter.flush();
                        break;
                    case "How many bots you want to add?":
                        printWriter.println(new Scanner(System.in).nextLine());
                        printWriter.flush();
                        break;
                    case "Do you want to start?(y/n)":
                        printWriter.println(new Scanner(System.in).nextLine());
                        printWriter.flush();
                        break;
                    case "move":
                        printWriter.println(new Scanner(System.in).nextLine());
                        printWriter.flush();
                        break;
                    case "name?":
                        printWriter.println(new Scanner(System.in).nextLine());
                        printWriter.flush();
                        break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
    }
}
