package graphic.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
    private final Dimension size = new Dimension(1280,800);
    private GameController controller;

    private final boolean isHost;
    private final JPanel mainPanel = new JPanel();
    private GameDeckPanel deckPanel;
    private PlayerHandPanel[] handsPanels;
    private int hearts;
    private int gameSize;

    public GameFrame(int gameSize , GameController controller , boolean isHost){
        this.isHost = isHost;
        this.controller = controller;
        controller.setHost(isHost);
        controller.setGameFrame(this);
        controller.execute();
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);
        this.gameSize= gameSize;
        this.setSize(size);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void setupFourPlayerGame() {
        mainPanel.removeAll();
        deckPanel = new GameDeckPanel(controller);
        mainPanel.add(deckPanel , BorderLayout.CENTER);
        handsPanels[0] = new PlayerHandPanel(controller.getMyHand() , controller);
        handsPanels[1] = new PlayerHandPanel(controller.getPlayer1handSize());
        handsPanels[2] = new PlayerHandPanel(controller.getPlayer2handSize());
        handsPanels[3] = new PlayerHandPanel(controller.getPlayer3handSize());
        mainPanel.add(handsPanels[0] , BorderLayout.SOUTH);
        mainPanel.add(handsPanels[1] , BorderLayout.EAST);
        mainPanel.add(handsPanels[2] , BorderLayout.NORTH);
        mainPanel.add(handsPanels[3] , BorderLayout.WEST);
        this.repaint();
        this.revalidate();
    }

    private void setupThreePlayerGame() {
        mainPanel.removeAll();
        deckPanel = new GameDeckPanel(controller);
        mainPanel.add(deckPanel , BorderLayout.CENTER);
        handsPanels[0] = new PlayerHandPanel(controller.getMyHand() , controller);
        handsPanels[1] = new PlayerHandPanel(controller.getPlayer1handSize());
        handsPanels[2] = new PlayerHandPanel(controller.getPlayer2handSize());
        mainPanel.add(handsPanels[0] , BorderLayout.SOUTH);
        mainPanel.add(handsPanels[1] , BorderLayout.EAST);
        mainPanel.add(handsPanels[2] , BorderLayout.NORTH);
        repaint();
        revalidate();
    }

    private void setupTwoPlayerGame() {
        mainPanel.removeAll();
        deckPanel = new GameDeckPanel(controller);
        mainPanel.add(deckPanel , BorderLayout.CENTER);
        handsPanels[0] = new PlayerHandPanel(controller.getMyHand() , controller);
        handsPanels[1] = new PlayerHandPanel(controller.getPlayer1handSize());
        mainPanel.add(handsPanels[0] , BorderLayout.SOUTH);
        mainPanel.add(handsPanels[1] , BorderLayout.NORTH);
        this.repaint();
        this.revalidate();
    }


    public void setDeckPanel(GameDeckPanel deckPanel) {
        this.deckPanel = deckPanel;
    }

    public void setHandsPanels(PlayerHandPanel[] handsPanels) {
        this.handsPanels = handsPanels;
    }
    public void setupTheGame(){
        switch (gameSize){
            case 2:
                setupTwoPlayerGame();
                break;
            case 3:
                setupThreePlayerGame();
                break;
            case 4:
                setupFourPlayerGame();
                break;
        }
    }

    public boolean isHost() {
        return isHost;
    }

}
