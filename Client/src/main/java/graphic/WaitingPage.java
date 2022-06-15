package graphic;

import javax.swing.*;
import java.awt.*;

public class WaitingPage extends JFrame {
    private final WaitingPageController controller;
    private final Dimension size = new Dimension(200 ,275);
    private final JButton startButton = new JButton("Play");
    private JLabel statLabel = new JLabel();
    private final JPanel mainPanel = new JPanel();

    public WaitingPage(WaitingPageController controller) {
        this.controller = controller;
        this.setSize(size);
        this.setLayout(new BorderLayout());
        mainPanel.setBackground(Theme.getMainTheme().getMainColor());
        mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.PAGE_AXIS));
        this.add(mainPanel , BorderLayout.CENTER);

        JLabel label = new JLabel("Waiting for Players To Join...");
        label.setPreferredSize(new Dimension(500 , 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(Box.createRigidArea(new Dimension(50,25)));
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(50,75)));
        statLabel.setPreferredSize(new Dimension(500 ,50));
        statLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(statLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(50,75)));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(startButton);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setStatus();
    }
    public void setStatus(){
            statLabel.setText("Players: "+controller.getGameStatus());
            this.repaint();
            this.revalidate();
    }
}
