package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.rate.DatewiseRate;
import com.b2b.hotel.in.dto.rate.DatewiseRateIntoDatabase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface RateService {

    public ResponseEntity<BaseResponse<DatewiseRateIntoDatabase>> addRoomRateDatewise(DatewiseRate datewiseRate);
    public ResponseEntity<BaseResponse<DatewiseRateIntoDatabase>> updateOrInsertDatewiseRate(DatewiseRate datewiseRate);


}
