package logic.player;

import logic.game.Game;

public class Bot extends Player implements Runnable{
    private static int id;
    private long leastCardNumber = 100;
    private Game game;

    public Bot(Game game){
        this.name = "bot" + id;
        this.game = game;
    }


    @Override
    public void run() {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getNumber() < leastCardNumber) {
                leastCardNumber = hand.get(i).getNumber();
            }
        }
        try {
            Thread.sleep(leastCardNumber*3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.makeMove(leastCardNumber,this);
    }
}
