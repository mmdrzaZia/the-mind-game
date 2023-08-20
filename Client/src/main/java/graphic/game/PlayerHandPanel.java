package graphic.game;

import graphic.Theme;
import graphic.game.CardButton;
import graphic.game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerHandPanel extends JPanel implements ActionListener {
    private List<CardButton> hand;
    private String playerName;
    private int handSize;
    private GameController controller;

    public PlayerHandPanel(int handSize){
        this.handSize = handSize;

        for (int i = 0; i < handSize; i++) {
            this.add(new CardButton("-1"));
        }
        this.setPreferredSize(new Dimension(500 ,150));
        this.setBackground(Theme.getMainTheme().getMainColor());
    }


    public PlayerHandPanel(String[] myHand , GameController controller) {
        hand = new ArrayList<>();
        this.controller = controller;
        for (String card : myHand){
            CardButton cb = new CardButton(controller , card);
            cb.addActionListener(this);
            this.add(cb);
        }
        this.setPreferredSize(new Dimension(500 , 200));
        this.setBackground(Theme.getMainTheme().getMainColor());
    }

    public List<CardButton> getHand() {
        return hand;
    }

    public void setHand(List<CardButton> hand) {
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof CardButton){
            controller.makeMove("NUMBER-"+((CardButton)actionEvent.getSource()).getNumber());
        }
    }
}
