package com.lookable.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class LookableBaseException extends RuntimeException {

    private final HttpStatus httpStatus;

    protected LookableBaseException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
