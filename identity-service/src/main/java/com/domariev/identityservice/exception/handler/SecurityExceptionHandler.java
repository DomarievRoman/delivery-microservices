package com.domariev.identityservice.exception.handler;

import com.domariev.identityservice.exception.ApiError;
import com.domariev.identityservice.exception.InvalidTokenException;
import com.domariev.identityservice.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex,
                                                                  HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.UNAUTHORIZED;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex,
                                                                    HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.UNAUTHORIZED;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
                                                                     HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.UNAUTHORIZED;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException ex,
                                                                HttpServletRequest request) {
        HttpStatus notFoundStatus = HttpStatus.UNAUTHORIZED;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                notFoundStatus.getReasonPhrase(),
                notFoundStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, notFoundStatus);
    }

}
