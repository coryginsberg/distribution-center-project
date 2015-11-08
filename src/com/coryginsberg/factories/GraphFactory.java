package com.coryginsberg.factories;

import com.coryginsberg.Network;
import com.coryginsberg.graph.Graph;
import com.coryginsberg.managers.FacilityManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class GraphFactory {
    public static Graph<String> createGraph() {
        Graph<String> graph = new Graph<>();
        for (int i = 0; i < FacilityManager.getNumFacilities(); i++) {
            Network currentNetwork = FacilityManager.getFacilities().get(i);
            HashMap<Integer, String> connectedCities = currentNetwork.getConnectingCities();
            graph.addVertex(currentNetwork.getCity());
            for (Map.Entry<Integer, String> city : connectedCities.entrySet()) {
                graph.addVertex(city.getValue());
                graph.addEdge(currentNetwork.getCity(), city.getValue(), city.getKey());
            }
        }

        return graph;
    }
}
