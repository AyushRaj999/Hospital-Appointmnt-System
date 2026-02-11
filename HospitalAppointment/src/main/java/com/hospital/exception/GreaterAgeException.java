package com.hospital.exception;

public class GreaterAgeException extends RuntimeException {
    public GreaterAgeException(String message) {
        super(message);
    }
}
