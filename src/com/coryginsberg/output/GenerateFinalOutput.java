package com.coryginsberg.output;

import com.coryginsberg.importxml.ImportFiles;
import com.coryginsberg.importxml.UnexpectedNodeException;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class GenerateFinalOutput {
    public GenerateFinalOutput() throws FileAlreadyExistsException, UnexpectedNodeException, FileNotFoundException {
        ImportFiles.getInstance();

        new GenerateFacilityStatusOutput();
        new GenerateOrderOutput();
        //new GenerateFacilityStatusOutput();

    }
}
