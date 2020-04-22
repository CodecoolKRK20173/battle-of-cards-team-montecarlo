package com.myapp.battleofcards;

import java.util.LinkedList;

public abstract class Player{
    private LinkedList<Card> cards;

    Player(){
        cards = new LinkedList<Card>();}

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
}