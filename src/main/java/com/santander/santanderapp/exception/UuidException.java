package com.santander.santanderapp.exception;

public class UuidException extends RuntimeException {
    public UuidException(Throwable cause) {
        super(cause);
    }
    public UuidException(String msg) {
        super(msg);
    }
}
