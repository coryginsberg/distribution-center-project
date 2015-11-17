package com.coryginsberg.importxml;

import com.coryginsberg.managers.GraphManager;

/**
 * Created by Cory Ginsberg on 11/17/15.
 * Created for Logistics Application.
 */
public class ImportFiles {
    private static ImportFiles ourInstance;

    private ImportFiles() throws UnexpectedNodeException {
        Import importNetworkFile = new ImportNetworkFile();
        Import importFacilitiesFile = new ImportFacilitiesFile();
        Import importItemFile = new ImportItemFile();
        Import importInventoryFile = new ImportInventoryFile();

        importFacilitiesFile.importFile("src/com/coryginsberg/xml/Facilities.xml");
        importInventoryFile.importFile("src/com/coryginsberg/xml/FacilityInventory.xml");
        importItemFile.importFile("src/com/coryginsberg/xml/Items.xml");
        importNetworkFile.importFile("src/com/coryginsberg/xml/FacilityNetwork.xml");
        GraphManager.createGraph();
    }

    public static ImportFiles getInstance() throws UnexpectedNodeException {
        if (ourInstance == null) {
            ourInstance = new ImportFiles();
        }
        return ourInstance;
    }
}
