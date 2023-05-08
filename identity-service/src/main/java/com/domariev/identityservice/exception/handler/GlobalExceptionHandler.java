package com.domariev.identityservice.exception.handler;

import com.domariev.identityservice.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                          HttpServletRequest request) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getLocalizedMessage(),
                badRequestStatus.getReasonPhrase(),
                badRequestStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, badRequestStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest request) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        ValidationApiError validationApiError = buildValidationApiError(ex);
        ApiError apiError = new ApiError(request.getRequestURI(),
                validationApiError,
                badRequestStatus.getReasonPhrase(),
                badRequestStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, badRequestStatus);
    }

    @ExceptionHandler(PaymentCardAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handlePaymentCardAlreadyExistsException(PaymentCardAlreadyExistsException ex,
                                                                            HttpServletRequest request) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(request.getRequestURI(),
                ex.getMessage(),
                badRequestStatus.getReasonPhrase(),
                badRequestStatus.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, badRequestStatus);
    }

    private static ValidationApiError buildValidationApiError(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errorMessages.add(error.getDefaultMessage()));
        return ValidationApiError.builder()
                .validationMessage("Validation error occurred")
                .validationReasons(errorMessages)
                .build();
    }


}
