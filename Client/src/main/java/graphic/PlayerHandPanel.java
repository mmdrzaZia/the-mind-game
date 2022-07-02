package graphic;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerHandPanel extends JPanel {
    private List<CardPanel> hand;
    private String playerName;
    private int handSize;
    public PlayerHandPanel(int handSize){
        handSize = handSize;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK , 3));
    }

    public List<CardPanel> getHand() {
        return hand;
    }

    public void setHand(List<CardPanel> hand) {
        this.hand = hand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHandSize() {
        return handSize;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }
}
