package com.domariev.identityservice.exception.handler;

import com.domariev.identityservice.exception.ApiError;
import com.domariev.identityservice.exception.InvalidUserIdException;
import com.domariev.identityservice.exception.RoleAlreadyGrantedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleInvalidUserIdException(InvalidUserIdException ex,
                                                                 HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

    @ExceptionHandler(RoleAlreadyGrantedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleRoleAlreadyGrantedException(RoleAlreadyGrantedException ex,
                                                                      HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

}
