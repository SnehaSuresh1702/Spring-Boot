package com.assess1.tracker.exception;

public class EmptyResultDataAccessException extends Exception{
    public EmptyResultDataAccessException() {
        super();
    }

    public EmptyResultDataAccessException(String message) {
        super(message);
    }

    public EmptyResultDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResultDataAccessException(Throwable cause) {
        super(cause);
    }

    protected EmptyResultDataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
