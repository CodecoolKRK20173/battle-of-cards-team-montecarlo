
package com.myapp.battleofcards;

import java.util.ArrayList;
import java.util.Iterator;

public class Game{
    private ArrayList<Player> players;
    Human emptyHuman = new Human();
    String userChoice;

    Game(int numberOfPlayers){
        players = new ArrayList<Player>();
        this.players.add(new Human());
        for (int i = 1; i<numberOfPlayers; i++) {
            this.players.add(new Computer());
        }
        this.players.add(new Human());
        for (Player player : players) {
            if (player instanceof Human) {
                String userChoice = emptyHuman.chooseStat();
            }
        }
        switch (userChoice) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
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

    void showDecks(){
        for (Player player: players) {
            System.out.println("");
            System.out.println("Player cards:");
            System.out.println("");
            while (true) {
                try {
                    System.out.println(player.drawNext().getName());
                } catch (Exception e) {
                    break;
                }
            }
        }
    }
}
