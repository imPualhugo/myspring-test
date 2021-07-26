package com.banyuan.exception;

public class BookException extends RuntimeException implements MyException{
    public BookException() {
    }

    public BookException(String message) {
        super(message);
    }
}
