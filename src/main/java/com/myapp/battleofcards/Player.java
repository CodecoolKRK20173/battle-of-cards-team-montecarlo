package com.myapp.battleofcards;

import java.util.LinkedList;

public abstract class Player{
    private LinkedList<Card> cards;

    Player(){
        cards = new LinkedList<Card>();}

    void putAtBottom(Card card){
        this.cards.add(0, card);
    }

    public Card drawNext(){
        return this.cards.removeLast();
    }

    public abstract void chooseStat();
}