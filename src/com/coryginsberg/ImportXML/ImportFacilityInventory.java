package com.coryginsberg.importxml;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */

import com.coryginsberg.managers.InventoryManager;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ImportFacilityInventory {

    private static String id = null;
    private static String city = null;
    private static int quantity = 0;

    public void importInventory(File file) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                /* Comment out when done testing */
                createInventory(doc.getChildNodes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createInventory(NodeList nodeList) {

        ArrayList<HashMap<Integer, String>> facilityInventories = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            final Node tempNode = nodeList.item(i);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                // get node name and value
                if (tempNode.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int j = 0; j < nodeMap.getLength(); j++) {
                        final Node node = nodeMap.item(j);
                        if (node.getNodeName().equals("city")) {
                            if (node.getNodeValue() != null) {
                                city = node.getNodeValue();
                            }
                        }
                        if (node.getNodeName().equals("quantity")) {
                            quantity = Integer.parseInt(node.getNodeValue());
                            id = tempNode.getTextContent();
                            HashMap<Integer, String> facilityInventory = new HashMap<>();
                            facilityInventory.put(quantity, id);
                            facilityInventories.add(facilityInventory);
                        }
                    }
                }

                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    createInventory(tempNode.getChildNodes());
                }
            }
        }

        if (!facilityInventories.isEmpty()) {
            InventoryManager.inventoryManager.addInventory(city, facilityInventories);
        }
    }
}
