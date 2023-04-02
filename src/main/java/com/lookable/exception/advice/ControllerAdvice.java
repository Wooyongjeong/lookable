package com.lookable.exception.advice;

import com.lookable.exception.dto.ErrorResult;
import com.lookable.exception.model.LookableBaseException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(LookableBaseException.class)
    public ResponseEntity<ErrorResult> lookableExHandle(LookableBaseException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return new ResponseEntity<>(errorResult, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> methodArgumentNotValidExHandle(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        ErrorResult errorResult = new ErrorResult(String.join(", ", messages));
        return new ResponseEntity<>(errorResult, ex.getStatusCode());
    }

}
