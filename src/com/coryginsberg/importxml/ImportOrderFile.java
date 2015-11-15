package com.coryginsberg.importxml;

import com.coryginsberg.Order;
import com.coryginsberg.factories.OrderFactory;
import com.coryginsberg.managers.OrderManager;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class ImportOrderFile {

    OrderManager orderManager = new OrderManager();

    public void importFile(String fileName) throws UnexpectedNodeException {

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
                if (!entryName.equals("Order")) {
                    throw new UnexpectedNodeException("Unexpected node found: " + entryName);
                }

                // Get id attribute
                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String orderId = aMap.getNamedItem("id").getNodeValue();

                // Get Time nodes
                Element elem = (Element) facilityEntries.item(i);
                Node nodeTime = elem.getElementsByTagName("Time").item(0);
                int orderTime = Integer.parseInt(nodeTime.getTextContent());

                // Get Priority nodes
                Node nodePri = elem.getElementsByTagName("Priority").item(0);
                String orderPriority = nodePri.getTextContent();

                // Get Items nodes


                // Create a Network object using the data loaded from the XML File
                //Order order = OrderFactory.newOrder(orderTime, orderId, orderPriority, ImportNodes.importSubNodes(""));
                //orderManager.add(order);
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }
}
