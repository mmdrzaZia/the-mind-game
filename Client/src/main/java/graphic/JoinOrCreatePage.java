package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JoinOrCreatePage extends JFrame implements ActionListener {
    private final Dimension size = new Dimension(1280 , 800);
    private final JPanel rightPanel = new JPanel();
    private final  JPanel mainPanel = new JPanel();
    private final JPanel backPanel = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(mainPanel);
    private final JButton createNewGameButton = new JButton("Create Game");
    private final JoinOrCreatePageController controller;

    JoinOrCreatePage(JoinOrCreatePageController controller){
        this.controller = controller;
        controller.setFrame(this);
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
        controller.execute();
    }



    private void setGames(){
        getScrollPane().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getMainPanel().setLayout(new BoxLayout(getMainPanel() , BoxLayout.PAGE_AXIS));
    }

    private void setNewGame(){
        createNewGameButton.addActionListener(this);
        rightPanel.add(Box.createRigidArea(new Dimension(10 , 700)));
        rightPanel.add(new JLabel("Join or Create Game: "));
        rightPanel.add(Box.createRigidArea(new Dimension(10 , 30)));
        rightPanel.add(createNewGameButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createNewGameButton){
            CreateGameFrame createGameFrame = new CreateGameFrame(controller);
            this.dispose();
        }
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getBackPanel() {
        return backPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JButton getCreateNewGameButton() {
        return createNewGameButton;
    }
}
