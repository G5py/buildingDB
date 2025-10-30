package com.example.buildingdb.controller;


import com.example.buildingdb.aop.LogError;
import com.example.buildingdb.aop.LogInfo;
import com.example.buildingdb.dto.ErrorResponse;
import com.example.buildingdb.exception.BucketException;
import com.example.buildingdb.exception.InvalidDataException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URISyntaxException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @LogInfo
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(InvalidDataException e) {
        String exceptionMessage = e.getMessage();

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(exceptionMessage));
    }

    @LogError
    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<String> handleURISyntaxException() {
        return ResponseEntity
                .internalServerError()
                .body("Failed to create URI.");
    }

    @LogInfo
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        String exceptionMessage = e.getMessage();

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(exceptionMessage));
    }

    @LogInfo
    @ExceptionHandler(BucketException.class)
    public ResponseEntity<ErrorResponse> handleBucketException(BucketException e) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse(e.getMessage()));
    }
}
