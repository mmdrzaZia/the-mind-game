package graphic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    private final Dimension size = new Dimension(1280,800);
    private GameController controller;
    private final JPanel mainPanel = new JPanel();
    private GameDeckPanel deckPanel;
    private PlayerHandPanel[] hands;

    public GameFrame(int gameSize , GameController controller){
        this.controller = controller;
        new Thread(controller).start();
        mainPanel.setLayout(new BorderLayout());
        deckPanel = new GameDeckPanel();
        hands = new PlayerHandPanel[gameSize];

        this.setSize(size);
        this.setVisible(true);
        this.add(new JLabel("new game "+ gameSize + " player game"));
        this.setLocationRelativeTo(null);
//        switch (gameSize){
//            case 2:
//                setupTwoPlayerGame();
//                break;
//            case 3:
//                setupThreePlayerGame();
//                break;
//            case 4:
//                setupFourPlayerGame();
//                break;
//        }

    }

    private void setupFourPlayerGame() {
        mainPanel.add(hands[0] , BorderLayout.SOUTH);
        mainPanel.add(hands[1] , BorderLayout.NORTH);
    }

    private void setupThreePlayerGame() {
        mainPanel.add(hands[0] , BorderLayout.SOUTH);
        mainPanel.add(hands[1] , BorderLayout.EAST);
        mainPanel.add(hands[2] , BorderLayout.NORTH);

    }

    private void setupTwoPlayerGame() {
        mainPanel.add(hands[0] , BorderLayout.SOUTH);
        mainPanel.add(hands[2] , BorderLayout.NORTH);
    }

    public void updateGameFrame(){

    }

    private void setPanelInterface(){

    }
}
