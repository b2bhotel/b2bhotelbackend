package com.b2b.hotel.in.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class B2bHotelExceptionHandler implements B2bHotelExceptionHandlerInterface {

    @ExceptionHandler(B2bHotelException.class)
    @Override
    public ResponseEntity<Object> handleB2bHotelException(B2bHotelException ex) {
        B2bHotelErrorResponse errorResponse = B2bHotelErrorResponse.builder()
                .code("412")
                .message(ex.getMessage())
                .httpStatus(HttpStatus.PRECONDITION_FAILED).errorClass("ERROR")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<B2bHotelErrorResponse> handleGenericException(Exception ex) {
        B2bHotelErrorResponse errorResponse = B2bHotelErrorResponse.builder()
            .code("500")
            .message(ex.getMessage()) // Include the exception message
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).errorClass("ERROR")
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        // Check if the root cause is related to enum deserialization
        Throwable cause = ex.getMostSpecificCause();
        String errorMessage = "Invalid value provided.";

        if (cause.getMessage().contains("enum")) {
            errorMessage = "Invalid enum value: (" + ((InvalidFormatException) cause).getValue() + ") in ENUM: "+ ((InvalidFormatException) cause).getTargetType().getName();
        }


        B2bHotelErrorResponse errorResponse = B2bHotelErrorResponse.builder()
                .code("412").message(errorMessage)
                .platformMessage(ex.getMessage()) // Include the exception message
                .httpStatus(HttpStatus.PRECONDITION_FAILED).errorClass("ERROR")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
