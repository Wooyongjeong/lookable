package com.lookable.exception.model;

import org.springframework.http.HttpStatus;

public class NotFoundException extends LookableBaseException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
