package com.lookable.exception.model;

import com.lookable.exception.dto.ErrorCode;

public class ForbiddenException extends LookableBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E403_FORBIDDEN;

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
