package com.example.tametable.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class EntityNotFoundException extends RuntimeException{

    final HttpStatus NOT_FOUND_STATUS = NOT_FOUND;

    public HttpStatus getNOT_FOUND_STATUS() {
        return NOT_FOUND_STATUS;
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Long id) {
        super(message + id);
    }

    public EntityNotFoundException(String message, Integer id) {
        super(message + id);
    }
}
