package com.pavolpodstreleny.animalscare.exception;

public class EmployeeDoesNotExistException extends RuntimeException {

    public EmployeeDoesNotExistException(String message) {
        super(message);
    }

    public EmployeeDoesNotExistException(long employeeID) {
        super("Employee with ID " + employeeID + " does not exists");
    }

}
