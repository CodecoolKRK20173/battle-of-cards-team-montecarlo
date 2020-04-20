


public class Game{
    private ArrayList<Player> players;

    Game(int numberOfPlayers){
        for (int i = 0; i<numberOfPlayers; i++){
            this.players.add(New Player());
        }
        Deck deck = new Deck;
        Iterator deckIteraotr = deck.iterator();
        Iterator playerIterator = players.iterator();
        while (deckIteraotr.hasNext()){
            if (!playerIterator.hasNext()) {
                Iterator playerIterator = players.iterator();
            }
            playerIterator.next().putAtBottom(deckIteraotr.next());
        }

    }
}