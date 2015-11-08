package com.coryginsberg.managers;

import com.coryginsberg.Item;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ItemManager implements Manager<Item> {

    static ArrayList<Item> items = new ArrayList<>();

    public static void add(Item item) {
        items.add(item);
    }

    public static int getNumItems() {
        return items.size();
    }

    public static ArrayList<Item> get() {
        return items;
    }

    public static Item getItemAtIndex(int index) {
        return items.get(index);
    }


}