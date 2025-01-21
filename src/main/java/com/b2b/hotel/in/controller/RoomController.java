package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/addroom")
    public ResponseEntity<BaseResponse<Room>> addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping("/getallroombyflowId")
    public ResponseEntity<BaseResponse<List<Room>>> getAllRoomByFlowId() {

        return roomService.getAllRoomByFlowId();
    }
    @GetMapping("/getroombyroomIdandflowid")
    public ResponseEntity<BaseResponse<Room>> getRoomByRoomIdAndFlowId(@RequestParam String roomId,@RequestParam String flowId) {
        return roomService.getRoomByRoomIdAndFlowId(roomId,flowId);
    }
    @PutMapping("/updateroom")
    public ResponseEntity<BaseResponse<Room>> updateRoomByRoomId(@RequestBody Room room) {
        return roomService.updateRoomByRoomId(room);
    }
    @DeleteMapping("/deleteroom")
    public ResponseEntity<BaseResponse<Room>> deleteRoomByRoomId(@RequestParam String roomId) {
        return roomService.deleteRoomByRoomId(roomId);
    }
}