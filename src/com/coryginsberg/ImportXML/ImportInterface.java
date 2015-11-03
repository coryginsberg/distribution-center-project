package com.coryginsberg.importxml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * The Interface file of the Import Classes.
 * Implements the importFile() and createList() methods.
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/3/2015
 */
public interface ImportInterface {

    /**
     * Imports an XML file
     *
     * @param file the XML file to be imported
     */
    default void importFile(File file) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                createList(doc.getChildNodes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create Java-formatted lists of items imported from XML file.
     * @param nodeList List of child nodes that will be stored in Java-formatted lists.
     */
    void createList(NodeList nodeList);

}
