package com.example.studentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = DuplicateTeacherException.class)
    public ResponseEntity<Object> handleDuplicateTeacherIdException(DuplicateTeacherException ex, WebRequest webRequest) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();
        return new ResponseEntity<>(errorMessageDescription, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RollnoAlreadyExistsException.class)
    public ResponseEntity<Object> handleRollnoAlreadyExistsException(RollnoAlreadyExistsException ex, WebRequest webRequest) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();
        return new ResponseEntity<>(errorMessageDescription, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
