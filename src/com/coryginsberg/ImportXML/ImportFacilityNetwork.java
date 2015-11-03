package com.coryginsberg.importxml;

import com.coryginsberg.managers.FacilityManager;
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
public class ImportFacilityNetwork implements ImportInterface {

    private static String name = null;
    private static int rate = 0;
    private static int cost = 0;

    /**
     * Creates a computer-readable list for the items imported from the XML file
     *
     * @param nodeList The list of nodes that were received from the file imported.
     * @see ImportInterface
     */
    public void createList(NodeList nodeList) {

        ArrayList<HashMap<Float, String>> connectedCities = new ArrayList<>();

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
                            if (node.getNodeValue() != null) name = node.getNodeValue();
                        }
                        if (tempNode.getNodeName().equals("rate")) {
                            rate = Integer.parseInt(tempNode.getTextContent());
                            cost = Integer.parseInt(node.getNodeValue());
                        }
                        if (tempNode.getNodeName().equals("city") && tempNode.getParentNode().getNodeName().equals("links")) {
                            HashMap<Float, String> connectedCity = new HashMap<>();
                            connectedCity.put(Float.parseFloat(node.getNodeValue()), tempNode.getTextContent());
                            connectedCities.add(connectedCity);
                        }
                    }

                }
                // loop again if has child nodes
                if (tempNode.hasChildNodes()) createList(tempNode.getChildNodes());
            }
        }

        if (!connectedCities.isEmpty()) FacilityManager.facilityManager.addFacility(name, rate, cost, connectedCities);
    }
}
