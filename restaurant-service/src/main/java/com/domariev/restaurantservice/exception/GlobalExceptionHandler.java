package com.domariev.restaurantservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleRestaurantNotFoundException(RestaurantNotFoundException ex,
                                                                      HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                      HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(", ")),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

}
