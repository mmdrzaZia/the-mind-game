package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class WaitingPage extends JFrame implements ActionListener {
    private final WaitingPageController controller;
    private final Dimension size = new Dimension(300 ,250);
    private final JButton startButton = new JButton("Play");
    private final JPanel mainPanel = new JPanel();
    private final JPanel animationPanel = new JPanel();

    public WaitingPage(WaitingPageController controller) {
        this.controller = controller;
        this.setSize(size);
        this.setLayout(new BorderLayout());
        mainPanel.setBackground(Theme.getMainTheme().getMainColor());
        mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.PAGE_AXIS));
        this.add(mainPanel , BorderLayout.CENTER);

        JLabel label = new JLabel("Waiting for Players To Join...");
        label.setPreferredSize(new Dimension(200 , 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(Box.createRigidArea(new Dimension(50,25)));
        mainPanel.add(label);
        animationPanel.add(new JLabel(Theme.getLoadingAnimation()));
        animationPanel.setBackground(Theme.getMainTheme().getMainColor());
        animationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        animationPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        mainPanel.add(animationPanel);
        //mainPanel.add(Box.createRigidArea(new Dimension(50,10)));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setPreferredSize(new Dimension(150 , 25));
        mainPanel.add(startButton);
        startButton.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == startButton){
            controller.startGame();
            this.dispose();
            GameController gameController = new GameController(controller.getPrintWriter(), controller.getBufferedReader());
            GameFrame gameFrame = new GameFrame(controller.getGameSize(),gameController);
        }
    }

}