package com.banyuan.exception;

public class ChapterException extends RuntimeException implements MyException {
    public ChapterException() {
    }

    public ChapterException(String message) {
        super(message);
    }
}
