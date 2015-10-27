package com.coryginsberg;


import com.coryginsberg.Graph.GraphManager;
import com.coryginsberg.ImportXML.ImportFacilityInventory;
import com.coryginsberg.ImportXML.ImportFacilityNetwork;
import com.coryginsberg.ImportXML.ImportItemCatalog;

public class Main {

    public static void main(String[] args) throws Exception {
        ImportFacilityNetwork importFacilityNetwork = new ImportFacilityNetwork();
        importFacilityNetwork.importNetwork();
        GraphManager graphManager = new GraphManager();
        graphManager.createGraph();
        ImportFacilityInventory importFacilityInventory = new ImportFacilityInventory();
        importFacilityInventory.importInventory();
        ImportItemCatalog importItemCatalog = new ImportItemCatalog();
        importItemCatalog.importInventory();
    }
}
