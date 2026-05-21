package com.liverpool.mx.product.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundResourceException(NotFoundResourceException exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
            request.getDescription(false), 
            exception.getMessage(), 
            HttpStatus.NOT_FOUND.value(), 
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
            request.getDescription(false), 
            exception.getMessage(), 
            HttpStatus.BAD_REQUEST.value(), 
            LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
