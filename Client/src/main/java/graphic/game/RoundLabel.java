package graphic.game;

import javax.swing.*;
import java.awt.*;

public class RoundLabel extends JLabel {
    private int round;
    public RoundLabel(int round){
        this.round = round;
        this.setLayout(new GridBagLayout());
        this.add(new JLabel("Round: " + round));
        this.setPreferredSize(new Dimension(100, 100));
    }
}
