package com.lookable.exception.model;

import com.lookable.exception.dto.ErrorCode;

public class PasswordDoesNotMatchException extends LookableBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E401_UNAUTHORIZED_NOT_MATCH_PASSWORD;

    public PasswordDoesNotMatchException() {
        super(DEFAULT_ERROR_CODE);
    }

    public PasswordDoesNotMatchException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public PasswordDoesNotMatchException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public PasswordDoesNotMatchException(String message, Throwable cause) {
        super(message, cause, DEFAULT_ERROR_CODE);
    }

    public PasswordDoesNotMatchException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }
}
