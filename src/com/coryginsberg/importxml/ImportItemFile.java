package com.coryginsberg.importxml;

import com.coryginsberg.factories.ItemFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class ImportItemFile implements Import {

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
                if (!entryName.equals("Item")) {
                    throw new UnexpectedNodeException("Unexpected node found: " + entryName);
                }

                // Get a named nodes
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String itemID = facilityEntries.item(i).getTextContent();
                int itemPrice = Integer.parseInt(aMap.getNamedItem("Price").getNodeValue());

                // Create a Item object using the data loaded from the XML File
                ItemFactory.addItem(itemID, itemPrice);

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}