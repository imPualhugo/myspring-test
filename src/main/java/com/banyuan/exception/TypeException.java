package com.banyuan.exception;

public class TypeException extends RuntimeException implements MyException {
    public TypeException() {
    }

    public TypeException(String message) {
        super(message);
    }
}
