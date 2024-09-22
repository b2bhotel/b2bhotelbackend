package com.b2b.hotel.in.dto;

import com.b2b.hotel.in.model.StatusCodeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> implements Serializable {
    @Serial
    static final long serialVersionUID=1L;
    String status;
    Integer code;
    String message;
    T data;
    public BaseResponse<T> withData(T data){
        this.data=data;
        return this;
    }
    public BaseResponse<T> withCustomMessage(String message){
        this.message=message;
        return this;
    }
    public BaseResponse(String status,Integer code){
        super();
        this.status=status;
        this.code =code;
        this.message= StatusCodeEnum.getStatusMessageByCode(code);

    }

}
