package com.cmartin.learn.mybank.api;

/**
 * Created by cmartin on 31/03/2017.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
