package com.coryginsberg;

import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class Order {
    int time;
    String id;
    String destination;
    String priority;
    HashMap<Integer, String> items;

    public Order(int time, String id, String destination, String priority, HashMap<Integer, String> items) {
        this.time = time;
        this.id = id;
        this.destination = destination;
        this.priority = priority;
        this.items = items;
    }

    public String getID() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public String getDestination() {
        return destination;
    }

    public String getPriority() {
        return priority;
    }

    public HashMap<Integer, String> getItems() {
        return items;
    }
}
