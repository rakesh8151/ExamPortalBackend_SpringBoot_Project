package com.example.exam.exceptions;

import com.example.exam.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message= ex.getLocalizedMessage();
        ApiResponse response=ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserFoundException.class})
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(UserFoundException ex){
        String message= ex.getLocalizedMessage();
        ApiResponse response=ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
}
