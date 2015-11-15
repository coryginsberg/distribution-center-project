package com.coryginsberg.importxml;

/**
 * Created by Cory Ginsberg on 11/4/2015.
 * Created for Logistics Application.
 */
public class UnexpectedNodeException extends Exception {
    public UnexpectedNodeException() {
    }

    public UnexpectedNodeException(String message) {
        super(message);
    }

    public UnexpectedNodeException(Throwable throwable) {
        super(throwable);
    }

    public UnexpectedNodeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
