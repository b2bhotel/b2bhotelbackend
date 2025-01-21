package com.b2b.hotel.in.model;

import java.util.Arrays;
import java.util.Optional;

public enum StatusCodeEnum {

    OK(200, "OK"),
    CREATED(201, "CREATED"),

    ACCEPTED(202, "ACCEPTED"),

    PARTIAL_CONTENT(206, "PARTIAL_CONTENT"),
    READ_TIMEOUT(598, "READ_TIMEOUT"),
    NETWORK_CONNECTION_TIMEOUT(599, "NETWORK_CONNECTION_TIMEOUT"),

    NETWORK_ACCESS_TOKEN(511, "TOKEN GENERATION FAILED"),
    ILLEGAL_STATUS_CODE(403, "ILLEGAL_STATUS_CODE");
    private final String message;
    private final int code;

    private int apiCode;

    StatusCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

   /* StatusCodeEnum(int code, int apiCode, String message) {
        this.code = code;
        this.apiCode = apiCode;
        this.message = message;
    }*/

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public int getApiCode() {
        return apiCode;
    }

    /**
     * Method to get status message for given status code.
     *
     * @param code code.
     * @return status message.
     */
  public static String getStatusMessageByCode(Integer code) {
        Optional<StatusCodeEnum> statusMessageByCode = Arrays.stream(StatusCodeEnum.values())
            .filter(statusCodeAndMessage -> statusCodeAndMessage.code == code)
            .findFirst();
        return statusMessageByCode.isPresent() ? statusMessageByCode.get().getMessage()
            : StatusCodeEnum.ILLEGAL_STATUS_CODE.getMessage();
    }
    /*
     *//**
     * Method to get status message for given status code.
     *
     * @param code code.
     * @return status message.
     *//*
    public static Integer getApiStatusCodeByCode(Integer code) {
        Optional<StatusCodeEnum> statusMessageByCode = Arrays.stream(StatusCodeEnum.values())
            .filter(statusCodeAndMessage -> statusCodeAndMessage.code == code)
            .findFirst();
        return statusMessageByCode.isPresent() ? statusMessageByCode.get().getApiCode()
            : StatusCodeEnum.ILLEGAL_STATUS_CODE.getApiCode();
    }

    *//**
     * Method to get status message for given status code.
     *
     * @param code code.
     * @return status message.
     *//*
    public static Integer getStatusCode(Integer code) {
        Optional<StatusCodeEnum> statusMessageByCode = Arrays.stream(StatusCodeEnum.values())
            .filter(statusCodeAndMessage -> statusCodeAndMessage.code == code)
            .findFirst();
        return statusMessageByCode.isPresent() ? statusMessageByCode.get().getCode()
            : StatusCodeEnum.ILLEGAL_STATUS_CODE.getCode();
    }*/
}