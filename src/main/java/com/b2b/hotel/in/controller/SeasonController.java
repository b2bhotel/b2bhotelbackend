package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.dto.season.RoomIdActive;
import com.b2b.hotel.in.dto.season.Season;
import com.b2b.hotel.in.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class SeasonController {


    private final SeasonService seasonService;

    @PostMapping("/addSeason")
    public ResponseEntity<BaseResponse<Season>> createSeason(@RequestBody Season season) {
        return seasonService.saveSeason(season);
    }

    @GetMapping
    public ResponseEntity<List<Season>> getAllSeasons() {
        List<Season> seasons = seasonService.getAllSeasons();
        return ResponseEntity.ok(seasons);
    }

    @GetMapping("/getallroomforseason")
    public ResponseEntity<BaseResponse<List<RoomIdActive>>> getAllRoomByFlowId() {

        return seasonService.getAllRoomByFlowId();
    }

    @GetMapping("/getactiveroomselectedforseason")
    public ResponseEntity<BaseResponse<List<RoomIdActive>>> getAllRoomSelectedForSeason(@RequestBody List<RoomIdActive> roomIdActive) {

        return seasonService.getAllRoomSelectedForSeason(roomIdActive);
    }



}
