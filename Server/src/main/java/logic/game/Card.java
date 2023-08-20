package logic.game;

public class Card {
    private int number;

    public Card(int number){
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number+"";
    }
}
