package com.myapp.battleofcards;

//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
//import org.w3c.dom.Element;
//import java.io.File;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Main {
    public static void main(String[] args) {
//        try {
//            //creating a constructor of file class and parsing an XML file
//            File file = new File("/home/tomek/Desktop/cards.xml");
//            //an instance of factory that gives a document builder
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            //an instance of builder to parse the specified xml file
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(file);
//            doc.getDocumentElement().normalize();
//            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//            NodeList nodeList = doc.getElementsByTagName("card");
//            // nodeList is not iterable, so we are using for loop
//            for (int itr = 0; itr < nodeList.getLength(); itr++) {
//                Node node = nodeList.item(itr);
//                System.out.println("\nNode Name :" + node.getNodeName());
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) node;
//                    System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
//                    System.out.println("Max Speed: " + eElement.getElementsByTagName("maxSpeed").item(0).getTextContent());
//                    System.out.println("Acc: " + eElement.getElementsByTagName("acceleration").item(0).getTextContent());
//                    System.out.println("Hp: " + eElement.getElementsByTagName("horsePower").item(0).getTextContent());
//                    System.out.println("Engine: " + eElement.getElementsByTagName("engine").item(0).getTextContent());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            ObjectMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream(new File("/home/tomek/Desktop/cards.xml"));
            TypeReference<List<Card>> typeReference = new TypeReference<List<Card>>() {};
            List<Card> cards = mapper.readValue(inputStream, typeReference);
            for(Card p :cards) {
                System.out.println("name is "+p.getName()+" maxspd is "+p.getMaxSpeed()+" acc is "+p.getAcceleration()+" hp is "+p.getHorsePower()+" engine is "+p.getEngine());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
