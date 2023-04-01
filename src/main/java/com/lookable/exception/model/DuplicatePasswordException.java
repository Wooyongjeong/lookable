package com.lookable.exception.model;

import org.springframework.http.HttpStatus;

public class DuplicatePasswordException extends LookableBaseException {

    public DuplicatePasswordException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}
