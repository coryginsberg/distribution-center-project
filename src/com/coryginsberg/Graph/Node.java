package com.coryginsberg.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Cory Ginsberg on 10/26/2015.
 * Created for Logistics Application.
 */
public class Node<T> {

    private T vertex;

    private List<Edge<T>> edges;

    private Node<T> parent;

    private boolean isVisited;

    public Node(T vertex) {
        this.vertex = vertex;
        this.edges = new ArrayList<>();
    }

    public T vertex() {
        return vertex;
    }

    public boolean addEdge(Node<T> node, float weight) {
        if (hasEdge(node)) {
            return false;
        }
        Edge<T> newEdge = new Edge<>(this, node, weight);
        return edges.add(newEdge);
    }

    public boolean removeEdge(Node<T> node) {
        Optional<Edge<T>> optional = findEdge(node);
        return optional.isPresent() && edges.remove(optional.get());
    }

    public boolean hasEdge(Node<T> node) {
        return findEdge(node).isPresent();
    }

    private Optional<Edge<T>> findEdge(Node<T> node) {
        return edges.stream()
                .filter(edge -> edge.isBetween(this, node))
                .findFirst();
    }

    public List<Edge<T>> edges() {
        return edges;
    }

    public float getEdgeCount() {
        return edges.size();
    }

    public Node<T> parent() {
        return parent;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}