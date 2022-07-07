package graphic.game;

import javax.swing.*;
import java.awt.*;

public class StatusLabel extends JLabel {
    private String status;
    private JLabel label;
    public StatusLabel(String status){
        this.status = status;
        label = new JLabel(status);
        label.setFont(new Font("Sans-Serif" , Font.BOLD , 25));
        this.setLayout(new GridBagLayout());
        this.add(label);
        this.setPreferredSize(new Dimension(300 , 50));
    }

    public void setStatus(String status) {
        label.setText(status);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
