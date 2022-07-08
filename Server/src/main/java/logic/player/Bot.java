package logic.player;

import logic.game.Game;
import logic.game.GameStatus;

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
        while (game.getStatus().equals(GameStatus.RUNNING)) {
            if (!hand.isEmpty()) {
                leastCardNumber = getLowestCard().getNumber();
                try {
                    Thread.sleep(/*leastCardNumber **/ 4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.makeMove(leastCardNumber, this);
            }
            //game.setMove(true);
        }
    }
}
