package com.example.studentapp.exception;

public class EmailIdAlreadyExistsException extends RuntimeException {

    public EmailIdAlreadyExistsException(String message) {
        super(message);
    }
}
