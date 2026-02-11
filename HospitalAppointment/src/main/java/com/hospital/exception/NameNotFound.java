package com.hospital.exception;

public class NameNotFound extends RuntimeException {
    public NameNotFound(String message) {
        super(message);
    }
}
