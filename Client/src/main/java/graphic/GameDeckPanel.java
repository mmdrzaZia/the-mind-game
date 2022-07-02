package graphic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameDeckPanel extends JPanel {
    private List<CardPanel> downCards;
    private GameController controller;
    private int hearts;

    public GameDeckPanel(GameController controller){
        this.controller = controller;
        downCards = new ArrayList<>();
        for (String card : controller.getDeck()){
            downCards.add(new CardPanel(controller , card));
        }
        hearts = controller.getHearts();
        this.add(new JLabel(hearts+""));
        this.add(downCards.get(downCards.size()-1));
    }

}
