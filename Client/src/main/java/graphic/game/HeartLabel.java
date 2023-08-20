package graphic.game;

import graphic.Theme;

import javax.swing.*;
import java.awt.*;

public class HeartLabel extends JLabel {
    private int hearts;
    public HeartLabel(int hearts){
        this.hearts = hearts;
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(100 , 100));
        JLabel label = new JLabel();
        label.setText(this.hearts+"");
        label.setIcon(Theme.getHeartImage());
        this.add(label);
    }
}
