package com.bms.auth_service.exception;

public class AuthFailedException extends RuntimeException {

    public AuthFailedException(String message) {
        super(message);
    }
}
