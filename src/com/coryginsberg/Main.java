package com.coryginsberg;


import com.coryginsberg.Graph.GraphManager;
import com.coryginsberg.ImportXML.ImportFacilityNetwork;

public class Main {

    public static void main(String[] args) throws Exception {
        ImportFacilityNetwork importFacilityNetwork = new ImportFacilityNetwork();
        importFacilityNetwork.importNetwork();
        GraphManager graphManager = new GraphManager();
        graphManager.createGraph();
    }
}
