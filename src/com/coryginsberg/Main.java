package com.coryginsberg;


import com.coryginsberg.ImportXML.ImportFacilityNetwork;
import com.coryginsberg.Managers.FacilityManager;

public class Main {

    public static void main(String[] args) throws Exception {
        ImportFacilityNetwork importFacilityNetwork = new ImportFacilityNetwork();
        importFacilityNetwork.importNetwork();
        GraphManager graphManager = new GraphManager();
        graphManager.createGraph();
    }
}
