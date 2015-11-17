package com.coryginsberg.importxml;

import com.coryginsberg.Facility;
import com.coryginsberg.managers.NetworkManager;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Imports the requested XML file into the program.
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/3/2015
 */

public class ImportNetworkFile implements Import {

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
                if (!entryName.equals("Facility")) {
                    throw new UnexpectedNodeException("Unexpected node found: " + entryName);
                }

                // Get a node attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String facilityCity = aMap.getNamedItem("City").getNodeValue();

                ArrayList<Facility> facilities = NetworkManager.getFacilities();
                Element elem = (Element) facilityEntries.item(i);
                for (Facility facility : facilities) {
                    if (facility.getCity().equals(facilityCity)) {
                        // Get all nodes named "Link" - there can be 0 or more
                        NodeList linkedItemsList = elem.getElementsByTagName("LinkedCity");

                        for (int j = 0; j < linkedItemsList.getLength(); j++) {
                            if (linkedItemsList.item(j).getNodeType() == Node.TEXT_NODE) {
                                continue;
                            }

                            entryName = linkedItemsList.item(j).getNodeName();

                            if (!entryName.equals("LinkedCity")) {
                                throw new UnexpectedNodeException("Unexpected Node: " + entryName);
                            }

                            // Get some named nodes
                            elem = (Element) linkedItemsList.item(j);
                            String city = elem.getTextContent();
                            int distance = Integer.parseInt(elem.getAttributes().item(0).getTextContent());

                            HashMap<Facility, Integer> connectedFacility = new HashMap<>();
                            for (Facility addedFacility : NetworkManager.getFacilities()) {
                                if (addedFacility.getCity().equals(city)) {
                                    connectedFacility.put(addedFacility, distance);
                                    facility.addConnectingCity(connectedFacility);
                                    break;
                                }
                            }

                        }
                    }
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }
}
