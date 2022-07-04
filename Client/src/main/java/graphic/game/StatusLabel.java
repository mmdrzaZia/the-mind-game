package graphic.game;

import javax.swing.*;
import java.awt.*;

public class StatusLabel extends JLabel {
    private String status;
    public StatusLabel(String status){
        this.status = status;
        this.setLayout(new GridBagLayout());
        this.add(new JLabel(status));
        this.setPreferredSize(new Dimension(300 , 50));
    }

    public String getStatus() {
        return status;
    }
}
