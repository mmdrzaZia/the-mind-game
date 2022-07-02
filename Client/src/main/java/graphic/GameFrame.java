package graphic;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final Dimension size = new Dimension(1280,800);
    private GameController controller;
    private final JPanel mainPanel = new JPanel();
    private GameDeckPanel deckPanel;
    private PlayerHandPanel[] handsPanels;
    private int hearts;
    private int gameSize;

    public GameFrame(int gameSize , GameController controller){
        this.controller = controller;
        controller.setGameFrame(this);
        controller.execute();
        mainPanel.setLayout(new BorderLayout());
//        deckPanel = new GameDeckPanel(controller);
//        handsPanels = new PlayerHandPanel[gameSize];
        this.gameSize= gameSize;
        this.setSize(size);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
//
    }

    private void setupFourPlayerGame() {
        System.out.println("SETTING 4 PLAYER GAME");
        hearts = controller.getHearts();
        handsPanels[0] = new PlayerHandPanel(controller.getMyHand().length);
        handsPanels[1] = new PlayerHandPanel(controller.getPlayer1handSize());
        handsPanels[2] = new PlayerHandPanel(controller.getPlayer2handSize());
        handsPanels[3] = new PlayerHandPanel(controller.getPlayer3handSize());
        mainPanel.add(handsPanels[0] , BorderLayout.SOUTH);
        mainPanel.add(handsPanels[1] , BorderLayout.EAST);
        mainPanel.add(handsPanels[2] , BorderLayout.NORTH);
        mainPanel.add(handsPanels[3] , BorderLayout.WEST);

    }

    private void setupThreePlayerGame() {
        System.out.println("SETTING 3 PLAYER GAME");
        hearts = controller.getHearts();
        handsPanels[0] = new PlayerHandPanel(controller.getMyHand().length);
        handsPanels[1] = new PlayerHandPanel(controller.getPlayer1handSize());
        handsPanels[2] = new PlayerHandPanel(controller.getPlayer2handSize());
        mainPanel.add(handsPanels[0] , BorderLayout.SOUTH);
        mainPanel.add(handsPanels[1] , BorderLayout.EAST);
        mainPanel.add(handsPanels[2] , BorderLayout.NORTH);

    }

    private void setupTwoPlayerGame() {
        System.out.println("SETTING 2 PLAYER GAME");
        hearts = controller.getHearts();
        handsPanels[0] = new PlayerHandPanel(controller.getMyHand().length);
        handsPanels[1] = new PlayerHandPanel(controller.getPlayer1handSize());
        mainPanel.add(handsPanels[0] , BorderLayout.SOUTH);
        mainPanel.add(handsPanels[1] , BorderLayout.NORTH);
    }

    public void updateGameFrame(){
    }

    private void setPanelInterface(){

    }

    public void setDeckPanel(GameDeckPanel deckPanel) {
        this.deckPanel = deckPanel;
    }

    public void setHandsPanels(PlayerHandPanel[] handsPanels) {
        this.handsPanels = handsPanels;
    }
    public void setupTheGame(){
        System.out.println(gameSize);
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
}
