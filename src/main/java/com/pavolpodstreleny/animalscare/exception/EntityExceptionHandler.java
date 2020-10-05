package com.pavolpodstreleny.animalscare.exception;

import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { PetDoesNotExistException.class, CustomerDoesNotExistException.class,
            EmployeeDoesNotExistException.class, ScheduleDoesNotExistException.class })
    protected ResponseEntity<Object> handlePetDoesNotExists(RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, provideHTTPErrorBody(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value()),
                provideHTTPJSONHeader(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { TransformerFailedException.class })
    protected ResponseEntity<Object> handleTransfomerFailure(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                provideHTTPErrorBody(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value()), provideHTTPJSONHeader(),
                HttpStatus.BAD_REQUEST, request);
    }

    private HashMap<String, Object> provideHTTPErrorBody(String message, int status) {
        HashMap<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", message);
        errorBody.put("status", status);
        return errorBody;
    }

    private HttpHeaders provideHTTPJSONHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        return header;
    }
}