package com.hospital.exception;

public class PhoneNumberNotExistsException extends RuntimeException {
    public PhoneNumberNotExistsException(String message) {
        super(message);
    }
}
