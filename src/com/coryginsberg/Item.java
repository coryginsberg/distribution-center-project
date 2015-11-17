package com.coryginsberg;

/**
 * @author Cory Ginsberg
 * @version 1.0
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

    /**
     * Returns the id of the item requested.
     *
     * @return The id of the item requested.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the price of Item requested.
     * @return The Price of the item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the ID of the item as a String.
     * @return The ID of the item as a String.
     */
    @Override
    public String toString() {
        return getId();
    }
}
