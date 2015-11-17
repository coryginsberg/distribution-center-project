package com.coryginsberg.importxml;

import com.coryginsberg.Item;
import com.coryginsberg.factories.OrderFactory;
import com.coryginsberg.managers.ItemManager;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class ImportOrderFile {

    public static HashMap<Item, Integer> importSubNodes(Element elem, String elementTagName) throws UnexpectedNodeException {
        // Get all nodes named "Link" - there can be 0 or more
        HashMap<Item, Integer> stockedItems = new HashMap<>();
        NodeList linkedItemsList = elem.getElementsByTagName(elementTagName);
        for (int j = 0; j < linkedItemsList.getLength(); j++) {
            if (linkedItemsList.item(j).getNodeType() == Node.TEXT_NODE) {
                continue;
            }

            String entryName = linkedItemsList.item(j).getNodeName();

            if (!entryName.equals(elementTagName)) {
                throw new UnexpectedNodeException("Unexpected Node: " + entryName);
            }

            // Get some named nodes
            elem = (Element) linkedItemsList.item(j);
            String itemID = elem.getTextContent();
            int itemQuantity = Integer.parseInt(elem.getAttributes().item(0).getTextContent());

            ItemManager.getItems().forEach(item -> {
                if (item.getId().equals(itemID)) stockedItems.put(item, itemQuantity);

            });

        }
        return stockedItems;
    }

    public void importFile(String fileName) throws UnexpectedNodeException {

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
                if (!entryName.equals("Order")) {
                    throw new UnexpectedNodeException("Unexpected node found: " + entryName);
                }

                // Get id attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String orderId = aMap.getNamedItem("id").getNodeValue();

                // Get Time nodes
                Element elem = (Element) facilityEntries.item(i);
                Node nodeTime = elem.getElementsByTagName("Time").item(0);
                int orderTime = Integer.parseInt(nodeTime.getTextContent());

                // Get Destination nodes
                Node nodeDest = elem.getElementsByTagName("Destination").item(0);
                String orderDestination = nodeDest.getTextContent();

                // Get Priority nodes
                Node nodePri = elem.getElementsByTagName("Priority").item(0);
                String orderPriority = nodePri.getTextContent();

                // Create a Network object using the data loaded from the XML File
                OrderFactory.newOrder(orderTime, orderId, orderDestination, orderPriority, importSubNodes(elem, "Item"));
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }
}
