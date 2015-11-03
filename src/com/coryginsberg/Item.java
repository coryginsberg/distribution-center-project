package com.coryginsberg;

/**
 * @author Cory Ginsberg
 * @since 10/26/2015
 */

public class Item {

    private String id;
    private int price;

    public Item(String id, int price) {
        if (id == null)
            throw new RuntimeException("Item ID does not exist.");
        else
            this.id = id;

        if (price == 0)
            throw new RuntimeException("Item " + id + "does not have a price");
        else
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
        return getId();
    }
}
