package com.coryginsberg.importxml;

import com.coryginsberg.managers.FacilityManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ImportFacilityNetwork implements ImportInterface {

    private static String name = null;
    private static int rate = 0;
    private static int cost = 0;

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
                            if (node.getNodeValue() != null) {
                                name = node.getNodeValue();
                            }
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
                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    createList(tempNode.getChildNodes());
                }
            }
        }

        if (!connectedCities.isEmpty()) {
            FacilityManager.facilityManager.addFacility(name, rate, cost, connectedCities);
        }
    }
}
