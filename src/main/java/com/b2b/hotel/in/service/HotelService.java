package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.exception.B2bHotelException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface HotelService {
    ResponseEntity<BaseResponse<Hotel>> addHotel(Hotel hotel) throws B2bHotelException;
    ResponseEntity<BaseResponse<List<Hotel>>> getAllHotels();
    ResponseEntity<BaseResponse<Room>> addRoom(String hotelId, Room room);
    ResponseEntity<BaseResponse<Hotel>> getHotelByAgentIdAndHotelCode(String hotelCode);
}
