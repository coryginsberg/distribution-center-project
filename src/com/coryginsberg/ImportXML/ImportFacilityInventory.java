package com.coryginsberg.importxml;

import com.coryginsberg.managers.InventoryManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */
public class ImportFacilityInventory implements ImportInterface {

    private static String city = null;

    /**
     * Creates a computer-readable list for the items imported from the XML file
     *
     * @param nodeList The list of nodes that were received from the file imported.
     * @see ImportInterface
     */
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
                            int quantity = Integer.parseInt(node.getNodeValue());
                            String id = tempNode.getTextContent();
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
