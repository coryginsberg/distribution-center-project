package com.coryginsberg;

/**
 * Created by coryginsberg on 10/26/15.
 */

public class Item {

    private String id;
    private int price;

    public Item(String id, int price) {
        this.id = id;
        this.price = price;

    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item ID: " + getId() + "Price: $" + getPrice();
    }
}
