package com.seminario.store.exceptions.handlers;

import commons.exceptions.RecordNotFoundException;
import commons.exceptions.models.ErrorMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(RecordNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(status,
                status.value(),
                e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(status).body(errorMessageResponse);
    }

}
