package com.coryginsberg.managers;

import com.coryginsberg.Facility;
import com.coryginsberg.Network;
import com.coryginsberg.factories.GraphFactory;
import com.coryginsberg.graph.Graph;

import java.util.ArrayList;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/26/2015
 */
public class GraphManager {

    private static Graph<Facility> graph = new Graph<>();

    public static void createGraph() {
        graph = GraphFactory.createGraph();
    }

    public static String getShortestPath(Facility cityStart, Facility cityEnd) {
        return cityStart + ", " + graph.shortestPath(cityStart, cityEnd).toString().substring(1, graph.shortestPath(cityStart, cityEnd).toString().length() - 1);
    }

    public static float getTotalTime(float hoursDriving, float avgMph) {
        float totalTime = graph.getTotalWeight();

        return totalTime / (hoursDriving * avgMph);
    }

    // Converts the Vertexes (which are currently just a String for simplification) to their respective Facility
    // Throws an exception if a vertex does not match a facility on the XML (Should never happen but its just in case)
    public static ArrayList<Network> getAllFacilities() {
        graph.getVertexes().forEach(vertex -> {

        });
        return null;
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
