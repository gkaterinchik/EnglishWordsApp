package com.example.Words.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> handleExeption(Exception ex) {
        AppError error = new AppError(200, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ElementNotFound.class)
    public ResponseEntity<AppError> handleResourceNotFoundExeption(ElementNotFound ex) {
        String message = ex.getMessage();
        AppError error = new AppError(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SuchElementAlreadyExist.class)
    public ResponseEntity<AppError> handleSuchElementAlreadyExist(SuchElementAlreadyExist ex) {
        //String message = ex.getMessage();
        AppError error = new AppError(400, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
