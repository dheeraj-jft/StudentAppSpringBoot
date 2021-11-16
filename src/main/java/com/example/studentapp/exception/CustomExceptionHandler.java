package com.example.studentapp.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler(value = EmailIdAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailIdAlreadyExistsException ex, WebRequest webRequest) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();
        return new ResponseEntity<>(errorMessageDescription, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateEntryExistsException(DuplicateKeyException ex, WebRequest webRequest) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (errorMessageDescription == null)
            errorMessageDescription = ex.toString();
        return new ResponseEntity<>(errorMessageDescription, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
