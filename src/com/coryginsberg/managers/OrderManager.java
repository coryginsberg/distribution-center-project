package com.coryginsberg.managers;

import com.coryginsberg.Order;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class OrderManager implements Manager<Order> {

    private static ArrayList<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> get() {
        return orders;
    }
}
