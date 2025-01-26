package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.frontened.FrontenedRoomRateResponse;

import java.util.List;

public interface RateCustomRepository {
    public List<FrontenedRoomRateResponse> getFilteredRoomRates(List<String> flowIds, String startDate, String endDate);
}
