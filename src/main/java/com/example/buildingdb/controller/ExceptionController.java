package com.example.buildingdb.controller;


import com.example.buildingdb.dto.ErrorResponse;
import com.example.buildingdb.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> HandleInvalidDataException(InvalidDataException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
