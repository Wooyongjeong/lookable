package com.lookable.exception.model;

import com.lookable.exception.dto.ErrorCode;

public class NotFoundException extends LookableBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E404_NOT_FOUND;

    public NotFoundException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

}
