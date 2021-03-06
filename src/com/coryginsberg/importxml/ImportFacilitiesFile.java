package com.coryginsberg.importxml;

import com.coryginsberg.factories.FacilityFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Imports the requested XML file into the program.
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/3/2015
 */

public class ImportFacilitiesFile implements Import {

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

            final NodeList facilityEntries = doc.getDocumentElement().getChildNodes();
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

                // Get a named nodes
                Element elem = (Element) facilityEntries.item(i);
                Node rateNode = elem.getElementsByTagName("Rate").item(0);
                int facilityRate = Integer.parseInt(rateNode.getTextContent());
                int facilityCost = Integer.parseInt(rateNode.getAttributes().item(0).getTextContent());

                // Create a Facility object using the data loaded from the XML File
                FacilityFactory.addFacility(facilityCity, facilityRate, facilityCost);


            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}