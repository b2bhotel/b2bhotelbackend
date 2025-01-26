package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.frontened.FrontenedRoomRateResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FrontendHomePageService {
    ResponseEntity<BaseResponse<List<FrontenedRoomRateResponse>>> getHotelsListViewBasedOnLocationAndDate(String destination,
                                                                                                          String startDate,
                                                                                                          String endDate);
}
