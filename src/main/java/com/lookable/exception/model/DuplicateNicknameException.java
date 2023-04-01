package com.lookable.exception.model;

import org.springframework.http.HttpStatus;

public class DuplicateNicknameException extends LookableBaseException {

    public DuplicateNicknameException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}
