package com.pavolpodstreleny.animalscare.exception;

public class PetDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PetDoesNotExistException(String message) {
        super(message);
    }
}
