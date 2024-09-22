package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.dto.login.LoginDetails;
import com.b2b.hotel.in.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class LoginController {


    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> getLoginDetails(@RequestBody LoginDetails loginDetails) {
        return loginService.getLoginDetails(loginDetails);
    }
}
