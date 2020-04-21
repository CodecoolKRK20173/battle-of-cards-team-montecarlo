package com.myapp.battleofcards;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        int numberOfPlayers = 2;
        Game game = new Game(numberOfPlayers);
        game.showDecks();
    }
}
