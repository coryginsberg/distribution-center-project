package com.coryginsberg;

import com.coryginsberg.importxml.UnexpectedNodeException;

import java.nio.file.FileAlreadyExistsException;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class GenerateFinalOutput {
    public GenerateFinalOutput() throws FileAlreadyExistsException, UnexpectedNodeException {
        new GenerateFacilityStatusOutput();
        new GenerateOrderOutput();
    }
}
