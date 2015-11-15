package com.coryginsberg;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class Order {
    int time;
    String id;
    String destination;
    String priority;
    ArrayList<Item> items;

    public Order(int time, String id, String destination, String priority, ArrayList<Item> items) {
        this.time = time;
        this.id = id;
        this.destination = destination;
        this.priority = priority;
        this.items = items;
    }
}
