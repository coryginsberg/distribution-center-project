package com.coryginsberg.graph;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/26/2015
 */

import com.sun.istack.internal.Nullable;

import java.util.*;

public class Graph<T> {

    private final Map<T, Node<T>> adjacencyList;

    private float totalWeight = 0;

    private ArrayList<T> vertexes = new ArrayList<>();

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    private Map<T, Node<T>> getAdjacencyList() {
        return adjacencyList;
    }

    public boolean addVertex(T vertex) {
        if (getAdjacencyList().containsKey(vertex)) {
            return false;
        }
        getAdjacencyList().put(vertex, new Node<>(vertex));
        vertexes.add(vertex);
        return true;
    }

    public boolean addEdge(T vertex1, T vertex2, float weight) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            throw new RuntimeException("Vertex does not exist");
        }

        // add the edge
        Node<T> node1 = getNode(vertex1);
        Node<T> node2 = getNode(vertex2);
        return node1.addEdge(node2, weight);
    }

    public boolean containsVertex(T vertex) {
        return getAdjacencyList().containsKey(vertex);
    }

    @Override
    public String toString() {
        return getAdjacencyList().toString();
    }

    @Nullable
    public List<T> shortestPath(T startVertex, T endVertex) {
        // if nodes not found, return empty path
        if (!containsVertex(startVertex) || !containsVertex(endVertex)) {
            return null;
        }
        // run bfs on the graph
        runBFS(startVertex);

        List<T> path = new ArrayList<>();
        // trace path back from end vertex to start
        Node<T> end = getNode(endVertex);
        while (end != null && end != getNode(startVertex)) {
            path.add(end.vertex());
            totalWeight += end.findEdge(end.parent()).get().getWeight();
            end = end.parent();

        }
        // if end is null, node not found
        if (end == null) {
            return null;
        }

        Collections.reverse(path);

        return path;
    }

    private void runBFS(T startVertex) {
        if (!containsVertex(startVertex)) {
            throw new RuntimeException("Vertex does not exist.");
        }

        resetGraph();

        Queue<Node<T>> queue = new LinkedList<>();
        Node<T> start = getNode(startVertex);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node<T> first = queue.remove();
            first.setVisited(true);
            for (Edge<T> edge : first.edges()) {
                Node<T> neighbor = edge.toNode();
                if (!neighbor.isVisited()) {
                    neighbor.setParent(first);
                    queue.add(neighbor);

                }
            }
        }
    }

    private Node<T> getNode(T value) {
        return getAdjacencyList().get(value);
    }

    private void resetGraph() {
        getAdjacencyList().keySet().forEach(key -> {
            Node<T> node = getNode(key);
            node.setParent(null);
            node.setVisited(false);
        });
        totalWeight = 0.0f;
    }

    public float getTotalWeight() {
        return totalWeight;
    }
}