package com.coryginsberg;

import com.coryginsberg.importxml.ImportOrderFile;
import com.coryginsberg.importxml.UnexpectedNodeException;

import java.nio.file.FileAlreadyExistsException;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class GenerateOrderOutput {
    private static ImportOrderFile importOrderFile = new ImportOrderFile();

    public GenerateOrderOutput() throws FileAlreadyExistsException, UnexpectedNodeException {
        importOrderFile.importFile("src/com/coryginsberg/Orders.xml");

    }
}
