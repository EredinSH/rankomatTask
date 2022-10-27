package com.rankomat.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public  ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException exception) {
        return new ResponseEntity<>("Client doesn't exist", HttpStatus.BAD_REQUEST);
    }

}
