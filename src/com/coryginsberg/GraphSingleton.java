package com.coryginsberg;

/**
 * Created by Cory Ginsberg on 11/15/15.
 * Created for Logistics Application.
 */
public class GraphSingleton {
    private static GraphSingleton ourInstance = new GraphSingleton();

    private GraphSingleton() {
    }

    public static GraphSingleton getInstance() {
        return ourInstance;
    }
}
