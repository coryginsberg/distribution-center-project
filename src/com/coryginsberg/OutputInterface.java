package com.coryginsberg;

/**
 * @author Cory Ginsberg
 * @since 11/3/2015
 */

public interface OutputInterface {

    void printStatusOutputForCity(Network network);

    default String stringRemoveBrackets(String str) {
        return str.substring(1, str.length() - 1);
    }
}
