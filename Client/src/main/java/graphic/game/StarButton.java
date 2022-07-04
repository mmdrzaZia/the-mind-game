package graphic.game;

import graphic.Theme;

import javax.swing.*;
import java.awt.*;

public class StarButton extends JButton {
    private int star;
    public StarButton(int star){
        this.star = star;
        this.setLayout(new GridBagLayout());
        JLabel label = new JLabel();
        label.setIcon(Theme.getStarImage());
        label.setText(star+"");
        this.add(label);
        this.setPreferredSize(new Dimension(100 , 100));
    }
}
