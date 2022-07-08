package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame implements ActionListener {
    private final Dimension size = new Dimension(1280 , 800);
    private final JTextField textField = new JTextField();
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel();
    private final JButton button = new JButton("Enter");
    private final String welcomeText = "Welcome, Please Enter Your Name :";
    private final LandingPageController controller;

    public LandingPage(LandingPageController controller){
        this.controller = controller;
        controller.setPage(this);
        this.setSize(size);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(panel , BorderLayout.CENTER);
        panel.setLayout(null);
        label.setBounds(490, 320,246,48);
        button.setBounds(585,400,109,32);

        textField.setBounds(489,365,300,30);
        textField.setBorder(null);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        label.setText(welcomeText);
        button.addActionListener(this);
        panel.add(button);
        panel.add(textField);
        panel.add(label);
        panel.setBackground(Theme.getMainTheme().getMainColor());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button){
            if (textField.getText() != null && !textField.getText().equals("")){
                controller.setName(textField.getText());
                this.dispose();
                SwingUtilities.invokeLater(()-> {
                    JoinOrCreatePageController joinOrCreatePageController = new JoinOrCreatePageController(controller.getPrintWriter(), controller.getBufferedReader(), controller.token);
                    JoinOrCreatePage joinOrCreatePage = new JoinOrCreatePage(joinOrCreatePageController);
                });
            }
        }
    }
}
