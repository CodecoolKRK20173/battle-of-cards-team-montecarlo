package com.myapp.battleofcards;

import java.util.LinkedList;

public abstract class Player{
    private LinkedList<Card> cards;
    String name;

    Player(String name){
        cards = new LinkedList<Card>();
        this.name = name;
    }

    void putAtBottom(Card card){
        this.cards.add(0, card);
    }

    public int getDeckLen(){
        return this.cards.size();
    }

    public Card drawNext(){
        return this.cards.removeLast();
    }

    public abstract String chooseStat();

    public String getName() {
        return name;
    }
}