package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.rate.DatewiseRate;
import com.b2b.hotel.in.dto.rate.DatewiseRateIntoDatabase;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PutMapping("/addroomratedatewise")
    public ResponseEntity<BaseResponse<DatewiseRateIntoDatabase>> addRoomRateDatewise(@RequestBody DatewiseRate datewiseRate) {
       // return rateService.addRoomRateDatewise(datewiseRate);
        return rateService.updateOrInsertDatewiseRate(datewiseRate);
    }
}
