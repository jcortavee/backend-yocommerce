package com.seminario.store.exceptions.handlers;

import commons.exceptions.RecordNotFoundException;
import commons.exceptions.UserAlreadyExistsException;
import commons.exceptions.UserDoesNotExistException;
import commons.exceptions.models.ErrorMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(RecordNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        var response = new ErrorMessageResponse(status, status.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(UserAlreadyExistsException e) {
        var status = HttpStatus.BAD_REQUEST;
        var response = new ErrorMessageResponse(status, status.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(UserDoesNotExistException e) {
        var status = HttpStatus.BAD_REQUEST;
        var response = new ErrorMessageResponse(status, status.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }


//    @ExceptionHandler(FeignException.class)
//    public ResponseEntity<ErrorMessageResponse> handleException(FeignException e) {
//        var status = e.status();
//        var response = new ErrorMessageResponse(HttpStatus.resolve(status),
//                String.valueOf(status), e.getMessage(), LocalDateTime.now());
//
//        return ResponseEntity.status(status).body(response);
//    }

}
