package graphic.game;

import graphic.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameDeckPanel extends JPanel implements ActionListener{
    private List<CardButton> downCards;
    private HeartLabel heart;
    private StarButton star;
    private RoundLabel round;
    private StatusLabel status;
    private CardButton down;
    private PlayRoundButton playRound;
    private GameController controller;

    public GameDeckPanel(GameController controller){
        this.setBackground(Theme.getMainTheme().getMainColor());
        this.controller = controller;
        this.setLayout(new GridLayout(2,3));
        downCards = new ArrayList<>();
        for (String card : controller.getDeck()){
            downCards.add(new CardButton(controller , card));
        }
        star = new StarButton(controller.getStars());
        round = new RoundLabel(controller.getRound());
        status = new StatusLabel(controller.getGameStatus());
        down = downCards.get(downCards.size() - 1);
        heart = new HeartLabel(controller.getHearts());

        star.addActionListener(this);

        this.add(heart);this.add(star);this.add(round);this.add(status);this.add(down);
        if (controller.isHost()) {
            playRound = new PlayRoundButton();
            this.add(playRound);
            playRound.addActionListener(this);
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof StarButton){
            controller.makeMove("STAR");
        }
        if (actionEvent.getSource() instanceof  PlayRoundButton){
            if (controller.getGameStatus().equals("PAUSED")){
                controller.playRound();
            }else {
                JOptionPane.showMessageDialog(null , "You can't play round");
            }
        }
    }
}
