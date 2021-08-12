package com.playground.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static com.playground.demo.exception.ErrorResponse.ValidationError.fromException;
import static com.playground.demo.exception.ErrorResponse.errorResponse;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest webRequest) {
        logError(exception, webRequest);
        List<ErrorResponse.ValidationError> validationErrors = fromException(exception);
        return errorResponse("Validation error", validationErrors, fullUrl(webRequest));
    }

    private static String fullUrl(WebRequest webRequest) {
        return webRequest.toString();
    }

    private void logError(Exception exception, WebRequest webRequest) {
        String errorMessage = format("Rest error message: %s at path %s", exception.getMessage(), fullUrl(webRequest));
        log.error(errorMessage, exception);
    }
}