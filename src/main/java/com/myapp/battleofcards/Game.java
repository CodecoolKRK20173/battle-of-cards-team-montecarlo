package com.myapp.battleofcards;

import java.util.ArrayList;
import java.util.Iterator;

public class Game{
    private ArrayList<Player> players;
    InputProvider inputProvider = new InputProvider();

    String Game(int numberOfPlayers){
        players = new ArrayList<Player>();

        for (int i = 0; i<numberOfPlayers - 1; i++) {
            this.players.add(new Computer());
        }
        this.players.add(new Human());
        for (Player player : players) {
            if (player instanceof Human) {
                chooseStat();
            }
            switch (chooseStat()) {
                case (1) {
                    break;
                }
                case (2){
                    break;
                }
                case (3) {
                    break;
                }
                case (4) {
                    break;
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
            String input = "";
            String[] statsOptions = { "Max speed", "Acceleration", "Horse power", "Engine"};
            System.out.println("Choose one of following stats to compare with other players: \n" +
                    "0: Max speed \n" +
                    "1: Acceleration \n" +
                    "2: Horse power \n " +
                    "3: Engine");
            while (input.length() != 1 && !"0123".contains(input)) {
                input = inputProvider.getIntInput("How do you wanna play this round?");
            }
            final String stats = statsOptions[Integer.parseInt(input)];

            return stats;
        }
}