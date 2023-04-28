package com.domariev.locationservice.exception;

import com.google.maps.errors.InvalidRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleRestaurantNotFoundException(InvalidRequestException ex,
                                                                      HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }
}
