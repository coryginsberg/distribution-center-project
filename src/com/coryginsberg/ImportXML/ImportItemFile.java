package com.coryginsberg.importxml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class ImportItemFile {
    private String itemID;
    private String itemPrice;

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
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                // Get a named nodes
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                itemID = facilityEntries.item(i).getTextContent();
                itemPrice = aMap.getNamedItem("Price").getNodeValue();

                // TODO: Create a Item object using the data loaded from the XML File

                System.out.println("Item ID: " + itemID + "| Price: $" + itemPrice);

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }
}