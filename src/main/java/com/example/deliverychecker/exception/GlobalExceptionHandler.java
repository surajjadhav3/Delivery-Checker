package com.example.deliverychecker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAny(Exception ex, WebRequest req) {
        ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                req.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, WebRequest req) {
        String msg = ex.getBindingResult().getAllErrors().stream()
                .map(o -> o.getDefaultMessage())
                .findFirst().orElse("Validation error");
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                msg,
                req.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(InvalidPostalCodeException.class)
    public ResponseEntity<ApiError> handleInvalidPostal(InvalidPostalCodeException ex, WebRequest req) {
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "Invalid Postal Code",
                ex.getMessage(),
                req.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    public ResponseEntity<ApiError> handleServiceUnavailable(ServiceNotAvailableException ex, WebRequest req) {
        ApiError err = new ApiError(HttpStatus.NOT_FOUND.value(),
                "Service Unavailable",
                ex.getMessage(),
                req.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
