package graphic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPanel extends JButton implements ActionListener {
    private String number;
    private GameController controller;
    public CardPanel(GameController controller , String number){
        this.controller = controller;
        this.number = number;
        this.add(new JLabel(number+""));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

}
