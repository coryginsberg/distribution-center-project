package com.coryginsberg.importxml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */
public class ImportInventoryFile implements ImportInterface {

    private String facilityCity;
    private String itemID;
    private String itemQuantity;
    private ArrayList<String> stockedItems;

    /**
     * Imports the requested XML file into the program as a Facility.
     *
     * @param fileName The Facility XML File to import into the program.
     */
    public void importFile(String fileName) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facilityEntries.getLength(); i++) {
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                // Get a node attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                facilityCity = aMap.getNamedItem("City").getNodeValue();

                // Get a named nodes
                Element elem = (Element) facilityEntries.item(i);
                Node rateNode = elem.getElementsByTagName("Item").item(0);
                itemID = rateNode.getTextContent();
                itemQuantity = rateNode.getAttributes().item(0).getTextContent();

                // Get all nodes named "Link" - there can be 0 or more
                stockedItems = new ArrayList<>();
                NodeList linkedItemsList = elem.getElementsByTagName("Item");
                for (int j = 0; j < linkedItemsList.getLength(); j++) {
                    if (linkedItemsList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkedItemsList.item(j).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes
                    elem = (Element) linkedItemsList.item(j);
                    String itemID = elem.getTextContent();
                    String itemQuantity = elem.getAttributes().item(0).getTextContent();

                    // Create a string summary of the Items
                    stockedItems.add("Item ID: " + itemID + " | Quantity: " + itemQuantity);
                }

                // TODO: Create a Facility object using the data loaded from the XML File

                System.out.println("Facility: " + facilityCity + "\n" + stockedItems + "\n");

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }
}
