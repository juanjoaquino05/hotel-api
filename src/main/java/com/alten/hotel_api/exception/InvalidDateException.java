package com.alten.hotel_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidDateException(String message) {
        super(message);
    }
}
