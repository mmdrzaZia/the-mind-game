package logic.game;

public class Card {
    private CardType type;
    private int id;


    public Card(CardType type,int id){
        this.id = id;
        this.type = type;
    }

    public CardType getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
