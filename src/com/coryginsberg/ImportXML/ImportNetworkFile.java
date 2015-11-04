package com.coryginsberg.importxml;

import com.coryginsberg.managers.FacilityManager;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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

    /**
     * Imports the requested XML file into the program as a Facility.
     *
     * @param fileName The Facility XML File to import into the program.
     */
    public void importFile(String fileName) {

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
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }

                // Get a node attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String facilityCity = aMap.getNamedItem("City").getNodeValue();

                // Get a named nodes
                Element elem = (Element) facilityEntries.item(i);
                Node rateNode = elem.getElementsByTagName("Rate").item(0);
                int facilityRate = Integer.parseInt(rateNode.getTextContent());
                int facilityCost = Integer.parseInt(rateNode.getAttributes().item(0).getTextContent());

                // Get all nodes named "Link" - there can be 0 or more
                ArrayList<HashMap<Float, String>> linkedCities = new ArrayList<>();
                NodeList linkedCityList = elem.getElementsByTagName("LinkedCity");
                for (int j = 0; j < linkedCityList.getLength(); j++) {
                    if (linkedCityList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkedCityList.item(j).getNodeName();
                    if (!entryName.equals("LinkedCity")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes
                    elem = (Element) linkedCityList.item(j);
                    String linkedCity = elem.getTextContent();
                    int linkedDistance = Integer.parseInt(elem.getAttributes().item(0).getTextContent());

                    // Create a string summary of the book
                }

                // TODO: Create a Facility object using the data loaded from the XML File
                FacilityManager.facilityManager.addFacility(facilityCity, facilityRate, facilityCost, linkedCities);
                //System.out.println("Facility: " + facilityCity + " | Rate: " + facilityRate + "\nAt Cost: $" + facilityCost + "\n" + linkedCities + "\n");

            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }
}
