package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.BaseResponseBuilder;
import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.dto.login.LoginDetails;
import com.b2b.hotel.in.exception.B2bHotelException;
import com.b2b.hotel.in.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    @Override
    public ResponseEntity<BaseResponse<String>> getLoginDetails(LoginDetails loginDetails) {

       String agentId =loginRepository.findByUserNameAndPassword(loginDetails.getUserName(), loginDetails.getPassword())
                .map(LoginDetails::getAgentId)
                .orElseThrow(() -> new B2bHotelException("Invalid username or password."));
        MDC.put("agentId",agentId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("AgentId", agentId);
        return ResponseEntity.ok()
                .headers(headers)
                .body(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successful", null));
//        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfull","hello"),HttpStatus.OK);
    }
}
