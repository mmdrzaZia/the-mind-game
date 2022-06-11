package logic.player;

public class Bot extends Player implements Runnable{
    private static int id;

    public Bot(){
        this.name = "bot" + id;
    }
    @Override
    public void run() {

    }
}
