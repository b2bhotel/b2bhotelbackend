package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.dto.login.LoginDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LoginService {

    ResponseEntity<BaseResponse<String>> getLoginDetails(@RequestBody LoginDetails loginDetails);
}
