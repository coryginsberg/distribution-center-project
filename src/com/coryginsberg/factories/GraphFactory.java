package com.coryginsberg.factories;

import com.coryginsberg.Facility;
import com.coryginsberg.graph.Graph;
import com.coryginsberg.managers.NetworkManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class GraphFactory {

    public static Graph<Facility> createGraph() {
        Graph<Facility> graph = new Graph<>();
        for (int i = 0; i < NetworkManager.getNumFacilities(); i++) {
            Facility currentFacility = NetworkManager.getFacilities().get(i);
            HashMap<Facility, Integer> connectedCities = currentFacility.getConnectingCities();
            graph.addVertex(currentFacility);
            for (Map.Entry<Facility, Integer> city : connectedCities.entrySet()) {
                graph.addVertex(city.getKey());
                graph.addEdge(currentFacility, city.getKey(), city.getValue());
            }
        }

        return graph;
    }
}
