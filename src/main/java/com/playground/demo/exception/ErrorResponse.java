package com.playground.demo.exception;

import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

public class ErrorResponse {

    private String id;
    private String message;
    private String path;
    private List<ValidationError> validationErrors;

    private ErrorResponse() {

    }

    private ErrorResponse(String id, String message, String path, List<ValidationError> validationErrors) {
        this.id = id;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public static ErrorResponse errorResponse(String message, String path) {
        return new ErrorResponse(randomUUID().toString(), message, path, null);
    }

    public static ErrorResponse errorResponse(String message, List<ValidationError> validationErrors, String path) {
        return new ErrorResponse(randomUUID().toString(), message, path, validationErrors);
    }

    public static class ValidationError {

        private final String field;
        private final String message;

        private ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public static ValidationError validationError(String field, String message) {
            return new ValidationError(field, message);
        }

        public String getMessage() {
            return message;
        }

        public String getField() {
            return field;
        }

        public static List<ValidationError> fromException(MethodArgumentNotValidException exception) {
            return Optional.of(exception.getBindingResult())
                    .map(Errors::getFieldErrors)
                    .filter(fieldErrors -> !fieldErrors.isEmpty())
                    .map(fieldErrorList ->
                            fieldErrorList.stream()
                                    .map(fieldError -> {
                                        String field = fieldError.getField();
                                        String errorMessage = fieldError.getDefaultMessage();

                                        return validationError(field, errorMessage);
                                    }).collect(Collectors.toList())
                    ).orElse(null);
        }

    }

}
