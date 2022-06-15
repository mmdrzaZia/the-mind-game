package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGameFrame extends JFrame implements ActionListener {
    private final JoinOrCreatePageController controller;
    private final Dimension size = new Dimension(300 , 400);
    private final JComboBox<Integer> sizeOfGame;
    private final JPanel gameSizePanel = new JPanel();
    private final JButton createButton = new JButton("Create");
    private final JPanel createPanel = new JPanel();
    private final JPanel mainPanel = new JPanel();

    public CreateGameFrame(JoinOrCreatePageController controller) throws HeadlessException {
        this.controller = controller;
        this.setSize(size);
        this.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel , BoxLayout.PAGE_AXIS));
        //this.add(gameSizePanel);
        Integer[] sizes = new Integer[]{2,3,4};
        sizeOfGame = new JComboBox<>(sizes);
        gameSizePanel.setLayout(new BoxLayout(gameSizePanel , BoxLayout.LINE_AXIS));
        gameSizePanel.add(Box.createRigidArea(new Dimension(30 , 50)));
        gameSizePanel.add(new JLabel("Enter Size of The game"));
        gameSizePanel.add(Box.createHorizontalGlue());
        gameSizePanel.add(sizeOfGame);
        createPanel.add(createButton , BorderLayout.CENTER);
        createButton.setPreferredSize(new Dimension(100 ,25));
        mainPanel.add(gameSizePanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(createPanel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        createButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == createButton){
            controller.createGame(sizeOfGame.getSelectedIndex()+2);
            this.dispose();
            WaitingPageController waitingPageController = new WaitingPageController(controller.getPrintWriter(), controller.getBufferedReader());
            WaitingPage waitingPage = new WaitingPage(waitingPageController);
        }
    }
}
