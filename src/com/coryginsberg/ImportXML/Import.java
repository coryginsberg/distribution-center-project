package com.coryginsberg.importxml;

/**
 * The Interface file of the Import Classes.
 * Implements the importFile() and createList() methods.
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/3/2015
 */
public interface Import {

    /**
     * Imports an XML file
     *
     * @param fileName the XML file to be imported
     */
    void importFile(String fileName);

}
