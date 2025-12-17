package com.example.deliverychecker.exception;

public class ServiceNotAvailableException extends RuntimeException {
    public ServiceNotAvailableException(String message) {
        super(message);
    }
}
