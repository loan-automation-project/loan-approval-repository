package com.project.loan_approval.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.loan_approval.exception.ApprovalNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApprovalNotFoundException.class)
    public ResponseEntity<String> handleApplicationNotFound(ApprovalNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

