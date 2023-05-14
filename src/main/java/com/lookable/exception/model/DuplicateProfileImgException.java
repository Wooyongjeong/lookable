package com.lookable.exception.model;

import com.lookable.exception.dto.ErrorCode;

public class DuplicateProfileImgException extends LookableBaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E409_CONFLICT_PROFILE_IMG;

    public DuplicateProfileImgException() {
        super(DEFAULT_ERROR_CODE.getMessage(), DEFAULT_ERROR_CODE);
    }

}
