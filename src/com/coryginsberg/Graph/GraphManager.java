package com.coryginsberg.Graph;

import com.coryginsberg.Facility;
import com.coryginsberg.Managers.FacilityManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/26/2015.
 * Created for Logistics Application.
 */
public class GraphManager {

    public static GraphManager graphManager = new GraphManager();
    Graph<String> graph = new Graph<>();
    private FacilityManager facilityManager = FacilityManager.facilityManager;

    public void createGraph() {

        for (int i = 0; i < facilityManager.getNumFacilities(); i++) {
            Facility currentFacility = facilityManager.facilities().get(i);
            ArrayList<HashMap<Integer, String>> connectedCities = currentFacility.getConnectingCities();
            graph.addVertex(currentFacility.getCity());
            for (HashMap<Integer, String> city: connectedCities) {
                for (Integer distance: city.keySet()) {
                    graph.addVertex(city.get(distance));
                    graph.addEdge(currentFacility.getCity(), city.get(distance), distance);
                }
            }
        }

        System.out.println();
        System.out.println();
        System.out.println(graph.toString());
    }

    public void getShortestPath(String cityStart, String cityEnd) {
        System.out.println("City Start: " + cityStart + " | City End: " + cityEnd);
        System.out.println(graph.shortestPath(cityStart, cityEnd).toString());
    }


}
