
public class Player{
    private playerStack LinkedList<Card>;
    Player(){
        playerStack = new LinkedList<Card>;
    }
    void putAtBottom(Card card){
        this.playerStack.add(0, card);
    }
    public Card drawNext(){
        return this.cards.removeLast();
    }
}