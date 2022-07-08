package graphic;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Controller extends SwingWorker{
    protected String command;
    protected PrintWriter printWriter;
    protected BufferedReader bufferedReader;
    protected String token;
    public Controller(PrintWriter printWriter, BufferedReader bufferedReader, String token) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
        this.token = token;
    }

    public void sendMessageToServer(String msg){
        printWriter.println(token+"-"+msg);
        printWriter.flush();
        System.out.println("client : " + msg);
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void readData(String command){}


    @Override
    protected Object doInBackground() throws Exception {
        return null;
    }
}