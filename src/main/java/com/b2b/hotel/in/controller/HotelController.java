package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.exception.B2bHotelException;
import com.b2b.hotel.in.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/addhotels")
    public ResponseEntity<BaseResponse<Hotel>> addHotel(@RequestBody Hotel hotel) throws B2bHotelException {
        return hotelService.addHotel(hotel);
    }

    // Get all hotels
    @GetMapping("/getallhotels")
    public ResponseEntity<BaseResponse<List<Hotel>>> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Add a room to a hotel
    @PostMapping("/{flowId}/addrooms")
    public ResponseEntity<BaseResponse<Room>> addRoom(@PathVariable String hotelId, @RequestBody Room room) {
        return hotelService.addRoom(hotelId, room);
    }

    @GetMapping("/gethoteldetail")
    public ResponseEntity<BaseResponse<Hotel>> getHotelByAgentIdAndHotelCode(
            @RequestParam String hotelCode) {

        return hotelService.getHotelByAgentIdAndHotelCode(hotelCode);
    }
}
