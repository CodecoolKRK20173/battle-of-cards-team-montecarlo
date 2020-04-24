package com.myapp.battleofcards;

import com.github.tomaslanger.chalk.Chalk;
import org.javatuples.Pair;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Game{
    private final ArrayList<Player> players;

    Game(int numberOfHumanPlayers, int numberOfComputerPlayers){
        players = new ArrayList<Player>();
        String playername;
        for (int i = 0; i<numberOfHumanPlayers; i++){
            playername = "Human " + (i+1);
            this.players.add(new Human(playername));
        }
        for (int i = 0; i<numberOfComputerPlayers; i++) {
            playername = "Computer " + (i+1);
            this.players.add(new Computer(playername));
        }
        distributeCardsToPlayers(this.players);
    }

    void gamePlay(int latencyInMilliseconds) throws InterruptedException {
        int activePlayer = 0;
        while(this.players.get(activePlayer).getDeckLen() != 24) {
            activePlayer = processTurn(activePlayer, latencyInMilliseconds);
            if (this.players.get(activePlayer).getDeckLen() == 24)
                System.out.println(players.get(activePlayer).getName() + " WINS!");
        }
    }

    int emptyPlayerCleanup(int activePlayer){
        for (int i=0; i<players.size(); i++){
            if (players.get(i).getDeckLen()==0) {
                players.remove(players.get(i));
                i--;
                if (i < activePlayer) activePlayer--;
            }
        }
        return activePlayer;
    }

    int processTurn(int activePlayer, int latencyInMilliseconds) throws InterruptedException {
        activePlayer = emptyPlayerCleanup(activePlayer);
        ArrayList<Pair<Card, Player>> cards = new ArrayList<>();
        Map<String, Supplier<Optional<Pair<Card, Player>>>> statMap = assemblyStatMap(cards);
        printPlayersDeckSizes();
        cards = cardsOnBoard(activePlayer, cards);
        String stat = activePlayerChoice(activePlayer, cards, latencyInMilliseconds);
        otherPlayersRevealCards(activePlayer, cards, latencyInMilliseconds);

        Optional<Pair<Card, Player>> winner = statMap.get(stat).get();
        try {
            activePlayer = cards.indexOf(winner.get());
            Player trueWinner = winner.get().getValue1();
            System.out.println(players.get(activePlayer).getName() + " takes cards!\n");
            for (Pair<Card, Player> pair: cards){trueWinner.putAtBottom(pair.getValue0());};
        } catch (NoSuchElementException ignored){

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
        for (Player player : players) {
            cards.add(new Pair<Card, Player>(player.drawNext(), player));
        }
        return cards;
    }

    String activePlayerChoice(int activePlayer, ArrayList<Pair<Card, Player>> cards, int latencyInMilliseconds) throws InterruptedException {
        System.out.println(players.get(activePlayer).getName() + " has drawn: ");
        System.out.println(cards.get(activePlayer).getValue0().returnTable());
        String stat = players.get(activePlayer).chooseStat();
        System.out.println(players.get(activePlayer).getName() + " chose " + Chalk.on(stat).yellow().underline() + " to compare.\n");
        TimeUnit.MILLISECONDS.sleep(latencyInMilliseconds);
        return stat;
    }

    void otherPlayersRevealCards(int activePlayer, ArrayList<Pair<Card, Player>> cards, int latencyInMiliseconds) throws InterruptedException {
        System.out.println("Other player's cards:");
        for (int i = 0; i < players.size(); i++) {
            if (i != activePlayer) {
                System.out.println(players.get(i).getName() + " card: ");
                System.out.println(cards.get(i).getValue0().returnTable());
            }
        }
        TimeUnit.MILLISECONDS.sleep(latencyInMiliseconds);
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