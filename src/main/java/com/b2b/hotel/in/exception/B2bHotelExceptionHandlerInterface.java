package com.b2b.hotel.in.exception;

import org.springframework.context.MessageSourceAware;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface B2bHotelExceptionHandlerInterface {
    ResponseEntity<Object> handleB2bHotelException(B2bHotelException ex);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex);
}
