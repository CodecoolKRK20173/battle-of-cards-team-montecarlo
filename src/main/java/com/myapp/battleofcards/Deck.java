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
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> cards;

    Deck() {
        cards = new LinkedList<>();
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
        Card currentCard;
        for (int i = 0; i<24; i++) {
            currentCard = this.cards.get(i);
            this.cards.remove(currentCard);
            this.cards.add(generator.nextInt(23), currentCard);
        }
    }

    public LinkedList<Card> loadXML() {
        LinkedList<Card> cards = new LinkedList<>();

        try {
            String path = new File(Deck.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath().toString().replace("target/CardGame-1.0-SNAPSHOT-jar-with-dependencies.jar", "cards.xml");
            File deckFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(deckFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("card");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String cardName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    int maxSpeed = Integer.parseInt(eElement.getElementsByTagName("maxSpeed").item(0).getTextContent());
                    float acceleration = Float.parseFloat(eElement.getElementsByTagName("acceleration").item(0).getTextContent());
                    int horsePower = Integer.parseInt(eElement.getElementsByTagName("horsePower").item(0).getTextContent());
                    String engine = eElement.getElementsByTagName("engine").item(0).getTextContent();
                    this.cards.add(new Card(cardName, maxSpeed, acceleration, horsePower, engine));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return cards;
    }
}