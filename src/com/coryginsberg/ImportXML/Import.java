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

    void importFile(String fileName) throws UnexpectedNodeException;

}
