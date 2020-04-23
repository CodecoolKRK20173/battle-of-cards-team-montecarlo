package com.myapp.battleofcards;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Game begins.");
        InputProvider input = new InputProvider();
        int numberOfHumanPlayers = Integer.parseInt(input.getIntInput("Enter amount of Human agents in gameplay: "));
        int numberOfComputerPlayers = Integer.parseInt(input.getIntInput("Enter amount of Computer agents in gameplay: "));
        int latencyInMiliseconds = Integer.parseInt(input.getIntInput("Enter latency in milliseconds: \n 50 \n 100 \n 150 \n 200 \n 250 \n 500 \n 750 \n 1000 \n 1500 \n"));
        System.out.println("Total number of players:" + (numberOfHumanPlayers+numberOfComputerPlayers));
        try {
            String path = new File(Deck.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath().toString().replace("target/CardGame-1.0-SNAPSHOT-jar-with-dependencies.jar", "cards.xml");
            System.out.println("Game deck path: " + path);
        } catch (URISyntaxException e) {
            System.out.println("ERROR: no cards.xml in project directory");
            System.exit(0);
        }
        Game game = new Game(numberOfHumanPlayers, numberOfComputerPlayers);
        System.out.println("Deck generated, shuffled, players initialized, cards distributed equally.");
        game.gamePlay(latencyInMiliseconds);
    }
}
