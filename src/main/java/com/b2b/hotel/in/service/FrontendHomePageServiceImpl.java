package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.BaseResponseBuilder;
import com.b2b.hotel.in.dto.frontened.FrontenedRoomRateResponse;
import com.b2b.hotel.in.repository.HotelRepository;
import com.b2b.hotel.in.repository.RateRepository;
import com.b2b.hotel.in.utils.DestinationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontendHomePageServiceImpl implements FrontendHomePageService{

    private final RateRepository rateRepository;
    private final HotelRepository hotelRepository;
    @Override
    public ResponseEntity<BaseResponse<List<FrontenedRoomRateResponse>>> getHotelsListViewBasedOnLocationAndDate(
        String destination, String startDate, String endDate) {
        List<String> flowIds = DestinationUtil.getFlowIdsByDestination(destination, hotelRepository);
        if (!flowIds.isEmpty()) {
            //List<String> flowIds = List.of("66f9990b5655732d3e5b6541_A1_H11", "66f9a71d5655732d3e5b6542_A1_H12");
            //startDate = "2025-01-20";
            //endDate = "2025-01-25";
            List<FrontenedRoomRateResponse> response = rateRepository.getFilteredRoomRates(flowIds, startDate, endDate);
            return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
                "Room rate get successfully", response), HttpStatus.OK);
        }

        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
            "No hotel found for "+ destination, List.of(FrontenedRoomRateResponse.builder().build())), HttpStatus.OK);
        }
}
