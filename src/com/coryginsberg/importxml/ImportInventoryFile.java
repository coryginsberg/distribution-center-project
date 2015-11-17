package com.coryginsberg.importxml;

import com.coryginsberg.factories.InventoryFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */
public class ImportInventoryFile implements Import {

    public void importFile(String fileName) throws UnexpectedNodeException {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                throw new FileNotFoundException("XML File '" + fileName + "' cannot be found");
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
                    throw new UnexpectedNodeException("Unexpected node found: " + entryName);
                }

                // Get a node attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String facilityCity = aMap.getNamedItem("City").getNodeValue();

                // Get a named nodes
                Element elem = (Element) facilityEntries.item(i);

                // Create a Network object using the data loaded from the XML File
// Get all nodes named "Link" - there can be 0 or more
                HashMap<String, Integer> stockedItems = new HashMap<>();
                NodeList linkedItemsList = elem.getElementsByTagName("Item");
                for (int j = 0; j < linkedItemsList.getLength(); j++) {
                    if (linkedItemsList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkedItemsList.item(j).getNodeName();

                    if (!entryName.equals("Item")) {
                        throw new UnexpectedNodeException("Unexpected Node: " + entryName);
                    }


                    // Get some named nodes
                    elem = (Element) linkedItemsList.item(j);
                    String itemID = elem.getTextContent();
                    int itemQuantity = Integer.parseInt(elem.getAttributes().item(0).getTextContent());

                    // Create a string summary of the Items
                    stockedItems.put(itemID, itemQuantity);
                }

                InventoryFactory.addInventory(facilityCity, stockedItems);
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }
}
