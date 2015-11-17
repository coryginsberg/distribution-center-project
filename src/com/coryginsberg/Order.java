package com.coryginsberg;

import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class Order {
    private int time;
    private String id;
    private String destination;
    private String priority;
    private HashMap<Item, Integer> items;

    public Order(int time, String id, String destination, String priority, HashMap<Item, Integer> items) {
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

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return getID() + " @ time: " + getTime() + " to destination: " + getDestination() + " with priority: " + getPriority() + " for items: " + getItems().toString();
    }
}
