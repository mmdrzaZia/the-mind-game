package logic.player;

import logic.game.State;
import server.ClientHandler;

public class MyPlayer extends Player{

    private final ClientHandler handler;
    private boolean hasStarCard;
    private State state;

    public MyPlayer(String name , ClientHandler handler){
        this.name = name;
        this.handler = handler;
    }

    public ClientHandler getHandler() {
        return handler;
    }

    public void setHasStarCard(boolean hasStarCard) {
        this.hasStarCard = hasStarCard;
    }

    public boolean isHasStarCard() {
        return hasStarCard;
    }
}
