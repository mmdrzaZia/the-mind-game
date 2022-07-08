package graphic;

import graphic.game.GameController;
import graphic.game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingPage extends JFrame implements ActionListener {
    private final WaitingPageController controller;
    private final Dimension size = new Dimension(300 ,250);
    private final JButton startButton = new JButton("Play");
    private final JPanel mainPanel = new JPanel();
    private final JPanel animationPanel = new JPanel();
    private final JLabel playersJoinedLabel = new JLabel();
    private SwingWorker<Void, Integer> playersJoinedTask;
    private final int gameSize;
    private final boolean isHost;


    public WaitingPage(WaitingPageController controller , int gameSize , boolean isHost) {
        this.isHost = isHost;
        this.gameSize = gameSize;
        this.controller = controller;
        controller.setFrame(this);
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
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setPreferredSize(new Dimension(150 , 25));

        mainPanel.add(playersJoinedLabel);
        controller.execute();

        if (isHost){
            mainPanel.add(startButton);
            playersJoinedLabel.setText("0 players joined");
        }

        startButton.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == startButton){
            controller.startGame();
        }
    }

    public JLabel getPlayersJoinedLabel() {
        return playersJoinedLabel;
    }

    public int getGameSize() {
        return gameSize;
    }
}