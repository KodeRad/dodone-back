package com.dodone.dodone.controller.errors;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ExceptionNoSuchElement.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException
            () {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
                "No such element found", LocalDateTime.now());

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ExceptionSQLIntegrityConstraintViolation.class)
    public ResponseEntity<ErrorResponse> handleSqlIntegrityException
            () {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND,
                "Attempt to insert or update data in a database " +
                        "violates database's defined integrity constraint.",
                LocalDateTime.now());

        return new ResponseEntity<>(response, response.getStatus());
    }
}
