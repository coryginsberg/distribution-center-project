package com.coryginsberg.ImportXML;

import com.coryginsberg.Managers.FacilityManager;
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
public class ImportFacilityNetwork {

    private static String name = null;
    private static int rate = 0;
    private static int cost = 0;

    public void importNetwork(File file) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                /* Comment out when done testing */
                createFacility(doc.getChildNodes());
                //createFacilities(doc.getChildNodes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFacility(NodeList nodeList) {

        ArrayList<HashMap<Integer, String>> connectedCities = new ArrayList<>();

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
                            HashMap<Integer, String> connectedCity = new HashMap<>();
                            connectedCity.put(Integer.parseInt(node.getNodeValue()), tempNode.getTextContent());
                            connectedCities.add(connectedCity);
                        }
                    }

                }
                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    createFacility(tempNode.getChildNodes());
                }
            }
        }

        if (!connectedCities.isEmpty()) {
            FacilityManager.facilityManager.addFacility(name, rate, cost, connectedCities);
        }
    }
}
