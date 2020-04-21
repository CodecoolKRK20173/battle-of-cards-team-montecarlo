package com.myapp.battleofcards;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> cards;

    Deck() {
        cards = new LinkedList<Card>();
        this.cards.addAll(loadXML());
        shuffle();
    }

    public LinkedList<Card> getCards() {
        return cards;
    }


    public Card drawNext() {
        return this.cards.removeLast();
    }

    private void shuffle() {
        Random generator = new Random();
        for (Card card : cards) {
            this.cards.add(generator.nextInt(23), card);
            this.cards.remove(card);
        }
    }

    public LinkedList<Card> loadXML() {
        LinkedList<Card> cards = new LinkedList<Card>();

        try {
            File deckFile = new File("cards.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(deckFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("card");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String cardName = eElement.getAttribute("name");
                    int maxSpeed = Integer.parseInt(eElement.getAttribute("maxSpeed"));
                    float acceleration = Float.parseFloat(eElement.getAttribute("acceleration"));
                    int horsePower = Integer.parseInt(eElement.getAttribute("horsePower"));
                    // Engine engine = eElement.getAttribute("engine");
                    this.cards.add(new Card(cardName, maxSpeed, acceleration, horsePower));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return cards;
    }
}