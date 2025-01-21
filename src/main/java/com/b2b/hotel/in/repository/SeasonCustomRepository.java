package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.season.RoomIdActive;

import java.time.LocalDate;
import java.util.List;

public interface SeasonCustomRepository {
    boolean existsByFlowIdAndRoomDetailsAndDateOverlap(String flowId, List<RoomIdActive> roomDetails, LocalDate startDate, LocalDate endDate);
}
