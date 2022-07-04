import client.Client;
import graphic.Theme;

import javax.swing.*;
import java.net.ConnectException;

public class ClientMain {
    public static void main(String[] args) {
        if (JOptionPane.showConfirmDialog(null , "Do you want to connect to the server?" , "Connection" , JOptionPane.YES_NO_OPTION , JOptionPane.PLAIN_MESSAGE, Theme.getStartImage()) == JOptionPane.YES_OPTION) {
                Client client = new Client();
                client.run();
        }else {
            JOptionPane.showMessageDialog(null , "Can't play without a connection" ,"Connection" , JOptionPane.ERROR_MESSAGE , Theme.getStart2Image());
        }
    }
}
