package com.assess1.tracker.exception;

import com.assess1.tracker.entity.ErrorMessage;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> ResourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorMessage> EmptyResultDataAccessException(EmptyResultDataAccessException exception,
                                                                       WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


   /* @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String , String> HttpMessageNotReadableException(HttpMessageNotReadableException exception)
    {
        Map<String , String> error = new HashMap<>();
        Throwable cause = exception.getCause();
        if(cause instanceof MismatchedInputException){
            MismatchedInputException mismatchedInputException= (MismatchedInputException) cause;
            error.put("Please enter integer input" ,mismatchedInputException.getPath().get(0).getFieldName());
        }
        return error;
    }*/

    /*@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> HttpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                       WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }*/
}