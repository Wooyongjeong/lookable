package com.lookable.exception.advice;

import com.lookable.exception.dto.ErrorResult;
import com.lookable.exception.model.LookableBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(LookableBaseException.class)
    public ResponseEntity<ErrorResult> lookableExHandle(LookableBaseException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return new ResponseEntity<>(errorResult, ex.getHttpStatus());
    }

}
