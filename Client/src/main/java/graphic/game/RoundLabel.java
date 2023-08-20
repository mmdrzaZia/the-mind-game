package graphic.game;

import javax.swing.*;
import java.awt.*;

public class RoundLabel extends JLabel {
    private JLabel label;
    private int round;
    public RoundLabel(int round){
        this.round = round;
        this.setLayout(new GridBagLayout());
        label = new JLabel("Round: " + round);
        label.setFont(new Font("Sans-Serif" , Font.BOLD , 25));

        this.add(label);

        this.setPreferredSize(new Dimension(100, 100));
    }
}
