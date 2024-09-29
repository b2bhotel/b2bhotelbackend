package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.dto.season.RoomIdActive;
import com.b2b.hotel.in.dto.season.Season;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeasonService {
    ResponseEntity<BaseResponse<Season>> saveSeason(Season season);

    List<Season> getAllSeasons();

    ResponseEntity<BaseResponse<List<RoomIdActive>>> getAllRoomByFlowId();

    ResponseEntity<BaseResponse<List<RoomIdActive>>> getAllRoomSelectedForSeason(List<RoomIdActive> roomIdActive);
}
