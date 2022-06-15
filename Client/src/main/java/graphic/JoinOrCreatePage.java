package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinOrCreatePage extends JFrame implements ActionListener {
    private final Dimension size = new Dimension(1280 , 800);
    private JoinOrCreatePageController controller;
    private final JPanel rightPanel = new JPanel();
    private final  JPanel mainPanel = new JPanel();
    private final JPanel backPanel = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(mainPanel);
    private final JButton createNewGameButton = new JButton("Create Game");

    JoinOrCreatePage(JoinOrCreatePageController controller){
        this.controller = controller;
        this.setSize(size);
        this.setLayout(new BorderLayout());
        this.add(backPanel , BorderLayout.CENTER);

        backPanel.setLayout(new BorderLayout());
        backPanel.add(scrollPane , BorderLayout.CENTER);
        backPanel.add(rightPanel , BorderLayout.EAST);

        rightPanel.setBackground(Theme.getMainTheme().getSecondaryColor());
        rightPanel.setPreferredSize(new Dimension(320 , 800));

        this.setLocationRelativeTo(null);
        setGames();
        setNewGame();
        this.setVisible(true);
    }



    private void setGames(){
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.PAGE_AXIS));
        for (String game : controller.getCurrentGames()){
            String[] gameDetails = game.split("-");
            mainPanel.add(Box.createRigidArea(new Dimension(10 , 5)));
            mainPanel.add(new GamePanel(gameDetails[0] , gameDetails[1] , gameDetails[2]));
        }
    }

    private void setNewGame(){
        createNewGameButton.addActionListener(this);
        createNewGameButton.setVerticalAlignment(SwingConstants.CENTER);
        createNewGameButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightPanel.add(createNewGameButton);
    }

    public JoinOrCreatePageController getController() {
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createNewGameButton){
            CreateGameFrame createGameFrame = new CreateGameFrame(controller);
            this.dispose();
        }
    }


}
