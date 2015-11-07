package com.coryginsberg.factories;

import com.coryginsberg.Network;
import com.coryginsberg.graph.Graph;
import com.coryginsberg.managers.FacilityManager;

import java.util.ArrayList;
import java.util.HashMap;

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
            ArrayList<HashMap<Integer, String>> connectedCities = currentNetwork.getConnectingCities();
            graph.addVertex(currentNetwork.getCity());
            for (HashMap<Integer, String> city : connectedCities) {
                for (Integer distance : city.keySet()) {
                    graph.addVertex(city.get(distance));
                    graph.addEdge(currentNetwork.getCity(), city.get(distance), distance);
                }
            }
        }

        return graph;
    }
}
