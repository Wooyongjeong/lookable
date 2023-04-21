package com.lookable.exception.model;

import com.lookable.exception.dto.ErrorCode;

public class DuplicatePasswordException extends LookableBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E409_CONFLICT_PASSWORD;

    public DuplicatePasswordException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public DuplicatePasswordException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public DuplicatePasswordException(String message, Throwable cause) {
        super(message, cause, DEFAULT_ERROR_CODE);
    }

    public DuplicatePasswordException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

}
