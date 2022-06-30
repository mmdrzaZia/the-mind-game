package graphic;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Controller {
    protected String command;
    protected PrintWriter printWriter;
    protected BufferedReader bufferedReader;

    public Controller(PrintWriter printWriter, BufferedReader bufferedReader) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }

    public void sendMessageToServer(String msg){
        printWriter.println(msg);
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
}
