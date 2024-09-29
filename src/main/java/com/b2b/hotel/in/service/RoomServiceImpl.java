package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.BaseResponseBuilder;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.exception.B2bHotelException;
import com.b2b.hotel.in.repository.RoomRepository;
import com.b2b.hotel.in.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {


    private final RoomRepository roomRepository;

    private final HttpServletRequest request;


    public ResponseEntity<BaseResponse<Room>> addRoom(Room room) {
        Map<String,String> headerMap = RequestUtils.getHeaders(request);
        String flowId = headerMap.getOrDefault("flowid", null);
        room=RequestUtils.extractAndSetIds(flowId,room);
        if(StringUtils.isNotEmpty(room.getRoomName())) {
            String roomName = roomRepository.findRoomNameByFlowIdAndRoomName(flowId, room.getRoomName().trim());
            if (StringUtils.isEmpty(roomName)) {
                Room response = roomRepository.save(room);
                return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successfully", response), HttpStatus.OK);
            } else
                throw new B2bHotelException("pls check the Room Name as it is already present");
        }
        else {
            throw new B2bHotelException("Room name should not be Empty");
        }
        }

    @Override
    public ResponseEntity<BaseResponse<List<Room>>> getAllRoomByFlowId() {
        Map<String,String> headerMap = RequestUtils.getHeaders(request);
        String flowId = headerMap.getOrDefault("flowid", null);
        Optional<List<Room>> response= roomRepository.findRoomByFlowId(flowId);
        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfully",response.get()),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Room>> getRoomByRoomIdAndFlowId(String roomId,String flowId) {
        Optional<Room> response= roomRepository.findRoomByIdAndFlowId(roomId,flowId);
        if(response.isPresent())
        {
        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfully",response.get()),HttpStatus.OK);
    }
        else {
        throw  new B2bHotelException("Room Id: "+ roomId + " not present for given flowId: "+flowId+" ,please recheck again");
        }
    }

    @Override
    public ResponseEntity<BaseResponse<Room>> updateRoomByRoomId(Room room) {
        Optional<Room> roomPresent= roomRepository.findRoomById(room.getId());
        if (roomPresent.isPresent()) {
            Room response = roomRepository.save(room);
            return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Room detail updated successfully", response), HttpStatus.OK);
        }
        else {
            throw new B2bHotelException("This room id " +room.getId()+" does not exist");
        }
    }

    @Override
    public ResponseEntity<BaseResponse<Room>> deleteRoomByRoomId(String roomId) {
        Optional<Room> roomPresent= roomRepository.findRoomById(roomId);
        if (roomPresent.isPresent()) {
            roomRepository.deleteById(roomId);
            return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "RoomId: "+roomId+" deleted successfully", roomPresent.get()), HttpStatus.OK);
        }
        else {
            throw new B2bHotelException("This room id " +roomId+" does not exist");
        }
    }


}
