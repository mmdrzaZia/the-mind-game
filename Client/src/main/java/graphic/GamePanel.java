package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private String hostId;
    private String gameSize;
    private String playersIn;
    private final JButton joinButton = new JButton("Join");

    public GamePanel(String hostName, String gameSize, String playersIn) {
        this.hostId = hostName;
        this.gameSize = gameSize;
        this.playersIn = playersIn;
        this.setLayout(new BoxLayout(this , BoxLayout.LINE_AXIS));
        this.setPreferredSize(new Dimension(200 , 50));
        this.add(Box.createRigidArea(new Dimension(10,10)));
        this.add(new JLabel("HostID : " + hostId));
        this.add(Box.createRigidArea(new Dimension(400,10)));
        this.add(new JLabel("Players: "+playersIn+"/"+gameSize));
        this.add(Box.createHorizontalGlue());
        this.add(joinButton);
        joinButton.addActionListener(this);
        joinButton.setFocusable(false);
        this.setBorder(BorderFactory.createLineBorder(Theme.getMainTheme().getMainColor() , 2));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == joinButton){
            System.out.println("SALAM BADBAKHT");
        }
    }
}
