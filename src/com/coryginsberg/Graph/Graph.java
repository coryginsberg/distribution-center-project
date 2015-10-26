package com.coryginsberg.Graph;

/**
 * Created by Cory Ginsberg on 10/26/2015.
 * Created for Logistics Application.
 */

import com.sun.istack.internal.Nullable;

import java.util.*;

public class Graph<T> {

    private final Map<T, Node<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public Map<T, Node<T>> getAdjacencyList() {
        return adjacencyList;
    }

    public boolean addVertex(T vertex) {
        if (getAdjacencyList().containsKey(vertex)) {
            return false;
        }
        getAdjacencyList().put(vertex, new Node<>(vertex));
        return true;
    }

    public boolean addEdge(T vertex1, T vertex2) {
        return addEdge(vertex1, vertex2, 0);
    }

    public boolean addEdge(T vertex1, T vertex2, int weight) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            throw new RuntimeException("Vertex does not exist");
        }

        // add the edge
        Node<T> node1 = getNode(vertex1);
        Node<T> node2 = getNode(vertex2);
        return node1.addEdge(node2, weight);
    }

    public boolean removeVertex(T vertex) {
        if (!getAdjacencyList().containsKey(vertex)) {
            return false;
        }

        // get node to be removed
        final Node<T> toRemove = getNode(vertex);

        // remove all incoming edges to node
        getAdjacencyList().values().forEach(node -> node.removeEdge(toRemove));

        // remove the node
        getAdjacencyList().remove(vertex);
        return true;
    }

    public boolean removeEdge(T vertex1, T vertex2) {
        return !(!containsVertex(vertex1) || !containsVertex(vertex2)) && getNode(vertex1).removeEdge(getNode(vertex2));
    }

    public int vertexCount() {
        return getAdjacencyList().keySet().size();
    }

    public int edgeCount() {
        return getAdjacencyList().values()
                .stream()
                .mapToInt(Node::getEdgeCount)
                .sum();
    }

    public boolean containsVertex(T vertex) {
        return getAdjacencyList().containsKey(vertex);
    }

    public boolean containsEdge(T vertex1, T vertex2) {
        return !(!containsVertex(vertex1) || !containsVertex(vertex2)) && getNode(vertex1).hasEdge(getNode(vertex2));
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

        // reset the graph
        resetGraph();

        // init the queue
        Queue<Node<T>> queue = new LinkedList<>();
        Node<T> start = getNode(startVertex);
        queue.add(start);

        // explore the graph
        /*while (!queue.isEmpty()) {
            Node<T> first = queue.remove();
            first.setVisited(true);
            first.edges().foreach(edge -> {
                Node<T> neighbor = edge.toNode();
                if (!neighbor.isVisited()) {
                    neighbor.setParent(first);
                    queue.add(neighbor);
                }
            });
        }*/
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
    }
}