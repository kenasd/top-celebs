package com.alex.top.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Reset is running")
public class ResetIsActiveException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3792749056778914431L;

    public ResetIsActiveException(String message) {
        super(message);
    }
}
