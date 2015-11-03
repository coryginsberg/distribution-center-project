package com.coryginsberg.importxml;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */

import com.coryginsberg.managers.InventoryManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ImportFacilityInventory implements ImportInterface {

    private static String id = null;
    private static String city = null;
    private static int quantity = 0;

    public void createList(NodeList nodeList) {

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
                    createList(tempNode.getChildNodes());
                }
            }
        }

        if (!facilityInventories.isEmpty()) {
            InventoryManager.inventoryManager.addInventory(city, facilityInventories);
        }
    }
}
