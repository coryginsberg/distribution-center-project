package com.coryginsberg.graph;

/**
 * @author Cory Ginsberg
 * @since 10/26/2015
 */

public class Edge<T> {

    private Node<T> node1;

    private Node<T> node2;

    private float weight;

    public Edge(Node<T> node1, Node<T> node2, float weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public Node<T> fromNode() {
        return node1;
    }

    public Node<T> toNode() {
        return node2;
    }

    public boolean isBetween(Node<T> node1, Node<T> node2) {
        return (this.node1 == node1 && this.node2 == node2);
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return node2.vertex().toString() + " @ Distance: " + weight;
    }
}
