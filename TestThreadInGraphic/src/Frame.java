import javax.swing.*;
import java.awt.*;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

class MyFrame extends JFrame {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
    }
    private JPanel panel;
    private JLabel label;
    public MyFrame(){
        panel = new JPanel();
        label = new JLabel();
        this.add(panel);
        panel.add(label);
        this.setSize(new Dimension(500 ,500));
        this.setVisible(true);
    }
}
class joinedClientsTask extends SwingWorker<Integer , Integer>{
    private JLabel label;

    public joinedClientsTask(JLabel label){
        this.label = label;
    }
    @Override
    protected Integer doInBackground() throws Exception {
        int num = 100;
        while (num != 0){
            Thread.sleep(500);
            num--;
            label.setText(num+"");
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        super.process(chunks);
    }
}
class Server{
    public static void main(String[] args) {
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket socket = serverSocket.accept();
        InputStreamReader inputStreamReader = socket.getInputStream();
        OutputStreamWriter outputStreamWriter = socket.getOutputStream();

    }
}
class Client{
    public static void main(String[] args) {
        
    }
}