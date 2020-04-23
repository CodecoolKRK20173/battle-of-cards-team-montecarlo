package com.myapp.battleofcards;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Game begins.");
        int numberOfPlayers = 4;
        System.out.println("Number of players:" + numberOfPlayers);
        try {
            String path = new File(Deck.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath().toString().replace("target/CardGame-1.0-SNAPSHOT-jar-with-dependencies.jar", "cards.xml");
            System.out.println("Game deck path: " + path);
        } catch (URISyntaxException e) {
            System.out.println("ERROR: no cards.xml in project directory");
            System.exit(0);
        }
        Game game = new Game(numberOfPlayers);
        System.out.println("Deck generated, shuffled, players initialized, cards distributed equally.");
        game.gamePlay();
    }

//    void menu(){
//        InputProvider inputProvider = new InputProvider();
//        int option;
//        option = inputProvider.getIntInput("Enter a number to choose: ");
//        System.out.println("");
//        switch (option) {
//            case 1:
//                break;
//            case 2:
//                // High Score
//                break;
//            case 0:
//                // exitProgram();
//                break;
//
//        }
//    }
}
