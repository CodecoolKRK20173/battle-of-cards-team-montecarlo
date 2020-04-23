package com.myapp.battleofcards;

import com.github.tomaslanger.chalk.Chalk;
import org.javatuples.Pair;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Game{
    private ArrayList<Player> players;

    Game(int numberOfPlayers){
        players = new ArrayList<Player>();
        this.players.add(new Human("Player"));
        for (int i = 1; i<numberOfPlayers; i++) {
            this.players.add(new Computer("Computer " + i));
        }
        distributeCardsToPlayers(this.players);
    }

    void gamePlay() throws InterruptedException {
        int activePlayer = 0;
        while(this.players.get(activePlayer).getDeckLen() != 24)
        activePlayer = processTurn(activePlayer);

    }

    int processTurn(int activePlayer) throws InterruptedException {
        ArrayList<Pair<Card, Player>> cards = new ArrayList<>();
        Map<String, Supplier<Optional<Pair<Card, Player>>>> statMap = assemblyStatMap(cards);
        printPlayersDeckSizes();
        cards = cardsOnBoard(activePlayer, cards);
        String stat = activePlayerChoice(activePlayer, cards);
        otherPlayersRevealCards(activePlayer, cards);

        Optional<Pair<Card, Player>> winner = statMap.get(stat).get();
        try {
            activePlayer = cards.indexOf(winner.get());
            Player trueWinner = winner.get().getValue1();
            for (Pair<Card, Player> pair: cards){trueWinner.putAtBottom(pair.getValue0());};
        } catch (NoSuchElementException E){

        }

        for (int i=0; i<players.size(); i++){
            if (players.get(i).getDeckLen()==0) {
                players.remove(players.get(i));
                i--;
                if (i < activePlayer) activePlayer--;
            }
        }

        return activePlayer;
    }

    Map<String, Supplier<Optional<Pair<Card, Player>>>> assemblyStatMap(ArrayList<Pair<Card, Player>> cards){
        Map<String, Supplier<Optional<Pair<Card, Player>>>> statMap = new HashMap<>();
        statMap.put("Max speed", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getMaxSpeed())));
        statMap.put("Acceleration", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getAcceleration())));
        statMap.put("Horse power", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getHorsePower())));
        statMap.put("Engine", () -> cards.stream().max(Comparator.comparing(s -> s.getValue0().getEngine())));
        return statMap;
    }

    ArrayList<Pair<Card, Player>> cardsOnBoard(int activePlayer, ArrayList<Pair<Card, Player>> cards){
        for (int i=0; i < players.size(); i++){
            cards.add(new Pair<Card, Player>(players.get(i).drawNext(), players.get(i)));
        }
        return cards;
    }

    String activePlayerChoice(int activePlayer, ArrayList<Pair<Card, Player>> cards) throws InterruptedException {
        System.out.println(players.get(activePlayer).getName() + " has drawn: ");
        System.out.println(cards.get(activePlayer).getValue0().returnTable());
        String stat = players.get(activePlayer).chooseStat();
        System.out.println(players.get(activePlayer).getName() + " chose " + Chalk.on(stat).yellow().underline() + " to compare.\n");
        TimeUnit.MILLISECONDS.sleep(150);
        return stat;
    }

    void otherPlayersRevealCards(int activePlayer, ArrayList<Pair<Card, Player>> cards) throws InterruptedException {
        System.out.println("Other player's cards:");
        for (int i = 0; i < players.size(); i++) {
            if (i != activePlayer) {
                System.out.println(players.get(i).getName() + " card: ");
                System.out.println(cards.get(i).getValue0().returnTable());
            }
        }
        TimeUnit.MILLISECONDS.sleep(150);
    }

    void printPlayersDeckSizes() {
        for (Player player: players)
            System.out.println(player.getName() + " deck size is " + player.getDeckLen() + ".");
        System.out.println();
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