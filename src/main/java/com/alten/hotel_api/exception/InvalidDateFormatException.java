package com.alten.hotel_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidDateFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDateFormatException(String field) {
        super("Invalid value for " + field + ". You should use this format -> " + DATE_FORMAT_PATTERN);
    }
}