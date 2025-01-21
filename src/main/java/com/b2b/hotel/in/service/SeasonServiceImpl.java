package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.BaseResponseBuilder;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.dto.season.RoomIdActive;
import com.b2b.hotel.in.dto.season.Season;
import com.b2b.hotel.in.exception.B2bHotelException;
import com.b2b.hotel.in.repository.RoomRepository;
import com.b2b.hotel.in.repository.SeasonRepository;
import com.b2b.hotel.in.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final RoomRepository roomRepository;
    private final HttpServletRequest request;
    public ResponseEntity<BaseResponse<Season>> saveSeason(Season season)  {
        Map<String,String> headerMap = RequestUtils.getHeaders(request);
        String flowId = headerMap.getOrDefault("flowid", null);
        season.setFlowId(flowId);
        boolean hasOverlappingSeason = seasonRepository.existsByFlowIdAndRoomDetailsAndDateOverlap(
                season.getFlowId(),
                season.getRoomSelected(),
                season.getSeasonStartDate(),
                season.getSeasonEndDate()
        );

        if (hasOverlappingSeason) {
            throw new B2bHotelException("Overlapping season with the same roomId and flowId already exists.");
        }
        Season response = seasonRepository.save(season);

        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfull",response),HttpStatus.OK);
    }

    @Override
    public List<Season> getAllSeasons() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse<List<RoomIdActive>>> getAllRoomByFlowId()  {
        Map<String, String> headerMap = RequestUtils.getHeaders(request);
        String flowId = headerMap.getOrDefault("flowid", null);
   //   Optional<List<Room>> response = roomRepository.findRoomByFlowId(flowId);
//
//        List<RoomIdActive> roomIdActiveList = new ArrayList<>();
//        response.ifPresentOrElse(rooms -> rooms.forEach(room -> {
//
//            RoomIdActive roomIdActive = RoomIdActive.builder()
//                    .roomId(room.getId())
//                    .roomName(room.getRoomName())
//                    .status(Boolean.FALSE) // Assuming Room has a status field
//                    .build();
//            roomIdActiveList.add(roomIdActive);
//        }), ()->new B2bHotelException("Room not found for given flow id:"+flowId));
//        return ResponseEntity.ok()
//                .body(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successful", roomIdActiveList));
//

        Optional<List<Room>> response = roomRepository.findRoomByFlowId(flowId);

        if (response.isEmpty()) {
            throw new B2bHotelException("No rooms available for the given flow id: " + flowId);
        }

        List<RoomIdActive> roomIdActiveList = response.get().stream()
                .map(room -> RoomIdActive.builder()
                        .roomId(room.getId())
                        .roomName(room.getRoomName())
                        .status(Boolean.FALSE) // Assuming you want status to default to FALSE
                        .build())
                .collect(Collectors.toList());

        // Return response with list of RoomIdActive objects
        return ResponseEntity.ok().body(
                BaseResponseBuilder.buildResponse(
                        HttpStatus.OK.name(),
                        HttpStatus.OK.value(),
                        "Request is successful",
                        roomIdActiveList)
        );
    }

    @Override
    public ResponseEntity<BaseResponse<List<RoomIdActive>>> getAllRoomSelectedForSeason(List<RoomIdActive> roomIdActive) {
        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfull",roomIdActive),HttpStatus.OK);
    }
}
