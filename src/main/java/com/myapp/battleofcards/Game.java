package com.myapp.battleofcards;

import java.util.ArrayList;
import java.util.Iterator;

public class Game{
    private ArrayList<Player> players;
    InputProvider inputProvider = new InputProvider();

    Game(int numberOfPlayers){
        players = new ArrayList<Player>();
        for (int i = 0; i<numberOfPlayers - 1; i++) {
            this.players.add(new Computer());
        }
        this.players.add(new Human());
        for (Player player : players) {
            if (player instanceof Human) {
                chooseStat();
            }
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

    public String chooseStat() {
        System.out.println("Possibilities: \n 1.Max speed \n 2.Acceleration \n 3.Horse power \n  4.Engine");
        final String stats = inputProvider.getIntInput("How do you wanna play this round?");
        return stats;
    }
}