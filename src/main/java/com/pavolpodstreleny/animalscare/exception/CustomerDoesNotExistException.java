package com.pavolpodstreleny.animalscare.exception;

public class CustomerDoesNotExistException extends RuntimeException {
    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}