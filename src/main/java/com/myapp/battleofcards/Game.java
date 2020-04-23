package com.myapp.battleofcards;

import org.javatuples.Pair;

import java.util.*;
import java.util.function.Supplier;

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

    void assemblyStatMap(ArrayList<Pair<Card, Player>> cards, Map statMap){
        // TODO: interface composing hash map
    }

    int processTurn(int activePlayer){
        ArrayList<Pair<Card, Player>> cards = new ArrayList<>();

        // TODO: interface composing hash map

        Map<String, Supplier<Optional<Pair<Card, Player>>>> statMap = new HashMap<>();
        statMap.put("Max speed", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getMaxSpeed())));
        statMap.put("Acceleration", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getAcceleration())));
        statMap.put("Horse power", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getHorsePower())));
        statMap.put("Engine", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getEngine())));

        // TODO: print sizes of player's decks method

        for (int i=0; i < players.size(); i++) {
            System.out.println("Player " + Integer.toString(i+1) + " deck size " + Integer.toString(players.get(i).getDeckLen()) + ".");
        }

        // TODO: separate players drawing cards into method

        for (int i=0; i < players.size(); i++){
            cards.add(new Pair<Card, Player>(players.get(i).drawNext(), players.get(i)));
        }

        // TODO: active player action method ??

        System.out.println("Active player has drawn: ");
        System.out.println(cards.get(activePlayer).getValue0().returnTable());
        String stat = players.get(activePlayer).chooseStat();

        // TODO: other players reveal cards method

        for (int i=0; i< players.size(); i++){
            if (i != activePlayer) System.out.println(cards.get(i).getValue0().returnTable());
        }

        // choosing strongest card

        Optional<Pair<Card, Player>> winner = statMap.get(stat).get();
        try {
            activePlayer = cards.indexOf(winner.get());
            Player trueWinner = winner.get().getValue1();
            for (Pair<Card, Player> pair: cards){trueWinner.putAtBottom(pair.getValue0());};
        } catch (NoSuchElementException E){

        }

        for (int i=0; i<players.size(); i++){
            if (players.get(i).getDeckLen()==0) {players.remove(players.get(i));}
        }

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