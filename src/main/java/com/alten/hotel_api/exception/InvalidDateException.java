package com.alten.hotel_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidDateException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDateException(String message) {
        super(message);
    }
}
