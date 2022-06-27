package com.example.tametable.exception;

import org.springframework.security.core.AuthenticationException;

public class ConfirmEmailException extends AuthenticationException {
    public ConfirmEmailException(String explanation) {
        super(explanation);
    }

    public ConfirmEmailException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
