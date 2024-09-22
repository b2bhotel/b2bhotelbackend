package com.b2b.hotel.in.dto;

import java.io.Serial;
import java.io.Serializable;

public class BaseResponseBuilder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Method to set response with status and code.
     *
     * @param status status
     * @param code   code
     * @return BaseResponse
     */
    public static BaseResponse<?> setBaseResponseWithStatusAndCode(String status, Integer code) {
        return new BaseResponse<>(status, code);
    }

    /**
     * Method to set response with status, code and data.
     *
     * @param status status
     * @param code   code
     * @param data   data
     * @return BaseResponse
     */
    public static BaseResponse<?> setBaseResponseWithStatusAndCodeAndData(String status, Integer code, Object data) {
        return new BaseResponse<>(status, code).withData(data);
    }

    /**
     * Method to set response with status, code and custom message.
     *
     * @param status status
     * @param code   code
     * @param msg    msg
     * @return BaseResponse
     */
    public static BaseResponse<?> setBaseResponseWithStatusAndCodeAndCustomMessage(String status, Integer code,
                                                                                   String msg) {
        return new BaseResponse<>(status, code).withCustomMessage(msg);
    }

    /**
     * Method to set response with status, code, message and data.
     *
     * @param status status
     * @param code   code
     * @param msg    msg
     * @param data   data
     * @return BaseResponse
     */
    public static BaseResponse<?> setBaseResponseWithStatusAndCodeAndCustomMessageAndData(String status, Integer code,
                                                                                          String msg,
                                                                                          Object data) {
        return new BaseResponse<>(status, code).withCustomMessage(msg).withData(data);
    }

    /**
     * Method to set response with status, code, message and data.
     *
     * @param status status
     * @param code   code
     * @param msg    msg
     * @param data   data
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> buildResponse(String status, Integer code, String msg, T data) {
        return new BaseResponse<T>(status, code).withCustomMessage(msg).withData(data);
    }
}
