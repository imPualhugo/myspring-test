package com.banyuan.exception;

public class UserException extends RuntimeException implements MyException {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
