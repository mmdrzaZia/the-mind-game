package graphic;

import javax.swing.*;
import java.awt.*;

public class WaitingPage extends JFrame {
    private final WaitingPageController controller;
    private final Dimension size = new Dimension(500 ,500);
    private final JButton startButton = new JButton("Play");
    private JLabel statLabel = new JLabel();
    public WaitingPage(WaitingPageController controller) {
        this.controller = controller;
        this.add(new JLabel("Waiting for Players To Join..."));
        this.add(statLabel);
        this.add(startButton);
        this.setVisible(true);
        setStatus();
    }
    public void setStatus(){
            statLabel.setText(controller.getGameStatus());
            this.repaint();
            this.revalidate();
    }
}
