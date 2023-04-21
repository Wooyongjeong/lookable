package com.lookable.exception.model;

import com.lookable.exception.dto.ErrorCode;

public class DuplicateNicknameException extends LookableBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E409_CONFLICT_NICKNAME;

    public DuplicateNicknameException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public DuplicateNicknameException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public DuplicateNicknameException(String message, Throwable cause) {
        super(message, cause, DEFAULT_ERROR_CODE);
    }

    public DuplicateNicknameException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

}
