package com.b2b.hotel.in.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class B2bHotelErrorResponse {
    String code;
    String message;
    String platformMessage;
    HttpStatus httpStatus;
    String errorClass;
}
