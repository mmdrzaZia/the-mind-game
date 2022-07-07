package graphic.game;

import graphic.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardButton extends JButton{
    private String number;
    private GameController controller;

    public CardButton(GameController controller , String number){
        this.controller = controller;
        this.number = number;
        this.setLayout(new GridBagLayout());
        JLabel numberLabel = new JLabel(number+"");
        this.add(numberLabel);
        this.setPreferredSize(new Dimension(150  , 200));
    }
    public CardButton(String number){
        this.number = number;
        this.setLayout(new GridBagLayout());
        JLabel numberLabel = new JLabel(number+"");
        this.add(numberLabel);
        this.setPreferredSize(new Dimension(75 , 100));
        if (Integer.parseInt(number) == -1){
            numberLabel.setText("The Mind");
            this.setBackground(Theme.getMainTheme().getSecondaryColor());
        }
    }

    public GameController getController() {
        return controller;
    }

    public String getNumber() {
        return number;
    }

}