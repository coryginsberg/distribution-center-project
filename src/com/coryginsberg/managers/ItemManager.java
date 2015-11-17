package com.coryginsberg.managers;

import com.coryginsberg.Item;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ItemManager {

    private static ArrayList<Item> items = new ArrayList<>();

    public static void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public static ArrayList<Item> getItems() {
        return items;
    }


}