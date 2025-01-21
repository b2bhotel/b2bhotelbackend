package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.room.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RoomService {
    public ResponseEntity<BaseResponse<Room>> addRoom(Room room);

    ResponseEntity<BaseResponse<List<Room>>> getAllRoomByFlowId();

    ResponseEntity<BaseResponse<Room>> getRoomByRoomIdAndFlowId(String roomId,String flowId);

    ResponseEntity<BaseResponse<Room>> updateRoomByRoomId(Room roomId);

    ResponseEntity<BaseResponse<Room>> deleteRoomByRoomId(String roomId);
}
