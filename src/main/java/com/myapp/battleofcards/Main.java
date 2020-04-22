package com.myapp.battleofcards;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Game begins.");
        int numberOfPlayers = 4;
        System.out.println("Number of players:" + numberOfPlayers);
        try {
            String path = new File(Deck.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath().toString().replace("target/CardGame-1.0-SNAPSHOT-jar-with-dependencies.jar", "cards.xml");
            System.out.println("Game deck path: " + path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Game game = new Game(numberOfPlayers);
        System.out.println("Deck generated, shuffled, players initialized, cards distributed equally.");
        game.showDecks();
    }
}
