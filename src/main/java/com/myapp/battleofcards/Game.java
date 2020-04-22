package com.myapp.battleofcards;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game{
    private ArrayList<Player> players;

    Game(int numberOfPlayers){
        players = new ArrayList<Player>();
        this.players.add(new Human());
        for (int i = 1; i<numberOfPlayers; i++) {
            this.players.add(new Computer());
        }
        distributeCardsToPlayers(this.players);
    }

    void gamePlay() {
        int activePlayer = 0;
        while(this.players.get(activePlayer).getDeckLen() != 24)
        activePlayer = processTurn(activePlayer);

    }


    int processTurn(int activePlayer){
        Map<Integer, Pair<Card, Player>> cards = new HashMap<>();
        // TODO: wyswietl rozmiary talli wszystkich graczy

        for (int i=0; i < players.size(); i++){
            cards.put(cards.size(), new Pair<Card, Player>(players.get(i).drawNext(), players.get(i)));
        }

        System.out.println("Active player has drawn: ");
        System.out.println(cards.get(activePlayer).getValue0().returnTable());
        String stat = players.get(activePlayer).chooseStat();

        // TODO: wystwietl karty innych graczy

        for (int i = 0; i< players.size(); i++){

           // TODO: porownaj wszystkie karty po statsie i znajdz najwieksza
        }
        // TODO: gracz ktorego karta wygrala kladzie wszystkie na spod talii i staje sie active playerem
        return activePlayer;
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
        // this is developers method
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