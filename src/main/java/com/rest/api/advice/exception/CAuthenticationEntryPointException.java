package com.rest.api.advice.exception;

public class CAuthenticationEntryPointException extends RuntimeException {
    public CAuthenticationEntryPointException() {
        super();
    }
    public CAuthenticationEntryPointException(String msg) {
        super(msg);
    }

    public CAuthenticationEntryPointException(String msg, Throwable t) {
        super(msg, t);
    }

}
