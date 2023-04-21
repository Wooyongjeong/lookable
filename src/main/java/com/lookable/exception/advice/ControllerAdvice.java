package com.lookable.exception.advice;

import com.lookable.dto.ApiResponse;
import com.lookable.exception.dto.ErrorCode;
import com.lookable.exception.dto.ErrorResult;
import com.lookable.exception.model.LookableBaseException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.OK)
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SignatureException.class)
    public ApiResponse<ErrorResult> jwtSignatureExceptionHandle() {
        return ApiResponse.error(ErrorCode.E403_FORBIDDEN_TOKEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<ErrorResult> authenticationExceptionHandle() {
        return ApiResponse.error(ErrorCode.E403_FORBIDDEN_AUTHENTICATE);
    }

    @ExceptionHandler(BindException.class)
    private ApiResponse<ErrorResult> handleBadRequest(BindException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return ApiResponse.error(ErrorCode.E400_INVALID, errorMessage);
    }

    @ExceptionHandler(LookableBaseException.class)
    public ApiResponse<ErrorResult> lookableExHandle(LookableBaseException ex) {
        return ApiResponse.error(ex.getErrorCode(), ex.getMessage());
    }

}
