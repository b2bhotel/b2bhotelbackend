package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.frontened.FrontenedRoomRateResponse;
import com.b2b.hotel.in.service.FrontendHomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class FrontenedHomePageController {

    private final FrontendHomePageService frontendHomePageService;

    @GetMapping("/hotel-listing")
    public ResponseEntity<BaseResponse<List<FrontenedRoomRateResponse>>> getHotelsListViewBasedOnLocationAndDate(@RequestParam String destination,
                                                                                                                      @RequestParam String startDate,
                                                                                                                      @RequestParam String endDate)
    {
        return frontendHomePageService.getHotelsListViewBasedOnLocationAndDate(destination , startDate , endDate);
    }
}
