package com.coryginsberg.importxml;

import com.coryginsberg.managers.ItemManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ImportItems implements ImportInterface {
    private static String id = null;
    private static int price = 0;

    public void createList(NodeList nodeList) {

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
                        if (node.getNodeName().equals("price")) {
                            price = Integer.parseInt(node.getNodeValue());
                            id = tempNode.getTextContent();
                        }
                    }
                }

                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    createList(tempNode.getChildNodes());
                }
            }
        }

        ItemManager.itemManager.addItem(id, price);
    }
}
