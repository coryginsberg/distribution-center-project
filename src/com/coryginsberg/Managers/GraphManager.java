package com.coryginsberg.managers;

import com.coryginsberg.Facility;
import com.coryginsberg.graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/26/2015.
 * Created for Logistics Application.
 */
public class GraphManager {

    Graph<String> graph = new Graph<>();
    private FacilityManager facilityManager = FacilityManager.facilityManager;

    public void createGraph() {

        for (int i = 0; i < facilityManager.getNumFacilities(); i++) {
            Facility currentFacility = facilityManager.facilities().get(i);
            ArrayList<HashMap<Float, String>> connectedCities = currentFacility.getConnectingCities();
            graph.addVertex(currentFacility.getCity());
            for (HashMap<Float, String> city : connectedCities) {
                for (Float distance : city.keySet()) {
                    graph.addVertex(city.get(distance));
                    graph.addEdge(currentFacility.getCity(), city.get(distance), distance);
                }
            }
        }
    }

    public String getShortestPath(String cityStart, String cityEnd) {
        return cityStart + ", " + graph.shortestPath(cityStart, cityEnd).toString().substring(1, graph.shortestPath(cityStart, cityEnd).toString().length() - 1);
    }

    public float getTotalTime(float hoursDriving, float avgMph) {
        float totalTime = graph.getTotalWeight();

        return totalTime / (hoursDriving * avgMph);
    }

}
