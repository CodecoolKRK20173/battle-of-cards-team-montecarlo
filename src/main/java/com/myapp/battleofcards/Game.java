package com.myapp.battleofcards;

import java.util.ArrayList;
import java.util.Iterator;

public class Game{
    private ArrayList<Player> players;

    Game(int numberOfPlayers){
        players = new ArrayList<Player>();
        int activePlayer = 0;
        this.players.add(new Human());
        for (int i = 1; i<numberOfPlayers; i++) {
            this.players.add(new Computer());
        }
        distributeCardsToPlayers(this.players);

        //while(players.get(activePlayer).getDeckLen() != 0)
          //  activePlayer = processTurn(players.get(activePlayer));
    }

    int processTurn(Player player){
        int whoWon = 0;
        return whoWon;
    }

    void distributeCardsToPlayers(ArrayList<Player> players){
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
    void showDecks(){
        for (Player player: players) {
            System.out.println("");
            System.out.println("Player cards:");
            System.out.println("");
            while (true) {
                try {
                    System.out.println(player.drawNext().returnTable());
                } catch (Exception e) {
                    break;
                }
            }
        }
    }
}