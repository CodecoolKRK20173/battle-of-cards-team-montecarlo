package com.myapp.battleofcards;

import java.util.ArrayList;
import java.util.Iterator;

public class Game{
    private ArrayList<Player> players;

    Game(int numberOfPlayers){
        for (int i = 0; i<numberOfPlayers; i++) {
            this.players.add(new Player());
        }
        Deck deck = new Deck();
        Card currentCard;
        Iterator deckIterator = deck.getCards().iterator();
        Iterator playerIterator = players.iterator();
        while (deckIterator.hasNext()){
            if (!playerIterator.hasNext()) {
                playerIterator = players.iterator();
            }
            currentCard = (Card) deckIterator.next();
            ((Player)playerIterator.next()).putAtBottom(currentCard);
        }

    }
}