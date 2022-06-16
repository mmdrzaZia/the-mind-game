package graphic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    private final JPanel mainPanel = new JPanel();
    private GameDeckPanel deckPanel;
    private PlayerHandPanel[] hands;

    public GameFrame(int gameSize){
        mainPanel.setLayout(new BorderLayout());
        deckPanel = new GameDeckPanel();
        hands = new PlayerHandPanel[gameSize];
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
    }

    public void updateGameFrame(){

    }
}
