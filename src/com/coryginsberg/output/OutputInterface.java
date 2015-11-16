package com.coryginsberg.output;

/**
 * @author Cory Ginsberg
 * @since 11/3/2015
 */

public interface OutputInterface<T> {

    void printStatusOutput(T t);

    default String stringRemoveBrackets(String str) {
        return str.substring(1, str.length() - 1);
    }
}