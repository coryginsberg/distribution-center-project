package com.coryginsberg.importxml;

import com.coryginsberg.Item;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 11/4/2015.
 * Created for Logistics Application.
 */
public abstract class ImportNodes {

    public static HashMap<Integer, Item> importSubNodes(Element elem, String elementTagName) throws UnexpectedNodeException {
        // Get all nodes named "Link"
        // there can be 0 or more
        NodeList linkedItemsList = elem.getElementsByTagName(elementTagName);
        HashMap<Integer, String> item = new HashMap<>();
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

            // Add the Items to the HashMap
            item.put(itemQuantity, itemID);
        }
        return item;
    }
}
