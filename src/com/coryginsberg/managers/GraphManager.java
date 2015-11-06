package com.coryginsberg.managers;

import com.coryginsberg.factories.GraphFactory;
import com.coryginsberg.graph.Graph;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/26/2015
 */
public class GraphManager {

    private static Graph<String> graph = new Graph<>();

    public static void createGraph() {
        graph = GraphFactory.createGraph();
    }

    public String getShortestPath(String cityStart, String cityEnd) {
        return cityStart + ", " + graph.shortestPath(cityStart, cityEnd).toString().substring(1, graph.shortestPath(cityStart, cityEnd).toString().length() - 1);
    }

    public float getTotalTime(float hoursDriving, float avgMph) {
        float totalTime = graph.getTotalWeight();

        return totalTime / (hoursDriving * avgMph);
    }

}
