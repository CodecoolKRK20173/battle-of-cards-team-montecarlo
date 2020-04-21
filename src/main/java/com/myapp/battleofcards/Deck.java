package com.myapp.battleofcards;

import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> cards;

    Deck(){
        cards = new LinkedList<Card>();
        this.cards.addAll(loadXML());
        shuffle();
    }

    public LinkedList<Card> getCards() {
        return cards;
    }


    public Card drawNext(){
        return this.cards.removeLast();
    }

    private void shuffle(){
        Random generator = new Random();
        for (Card card: cards) {
            this.cards.add(generator.nextInt(23), card);
            this.cards.remove(card);
        }
    }

    public LinkedList<Card> loadXML(){
        LinkedList<Card> cards = new LinkedList<Card>();
        // read cards from xml document and add them to list
        return cards;
    }

}
