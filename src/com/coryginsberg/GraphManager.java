package com.coryginsberg;

import com.coryginsberg.Managers.Facility;
import com.coryginsberg.Managers.FacilityManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/26/2015.
 * Created for Logistics Application.
 */
public class GraphManager {
    FacilityManager facilityManager = FacilityManager.facilityManager;
    public void createGraph() {

        Graph<String> graph = new Graph<>();
        for (int i = 0; i < facilityManager.getNumFacilities(); i++) {
            System.out.println(i);
            Facility currentFacility = facilityManager.facilities().get(i);
            ArrayList<HashMap<Integer, String>> connectedCities = currentFacility.getConnectingCities();

            for (HashMap<Integer, String> city: connectedCities) {
                for (Integer distance: city.keySet()) {
                    graph.addNode(city.get(distance));
                }
            }
        }
        System.out.println(); System.out.println(graph.toString());
        System.out.println(); System.out.println(); System.out.println(facilityManager.facilities());
        //graph.add
    }
}
