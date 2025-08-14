package com.bms.theatre_service.exception;


import com.bms.theatre_service.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TheatreNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTheatreNotFound(TheatreNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex){
String message = ex.getBindingResult().getFieldError().getDefaultMessage();
         return new ResponseEntity<>(new ErrorResponse(message,HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
     }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){
        ex.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }


}
