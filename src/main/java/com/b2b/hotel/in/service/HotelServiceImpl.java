package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.BaseResponseBuilder;
import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.dto.room.Room;
import com.b2b.hotel.in.exception.B2bHotelException;
import com.b2b.hotel.in.repository.HotelRepository;
import com.b2b.hotel.in.repository.RoomRepository;
import com.b2b.hotel.in.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    private final HttpServletRequest request;

    @Override
    public ResponseEntity<BaseResponse<Hotel>> addHotel(Hotel hotel) throws B2bHotelException {
       // hotel.setId(new org.bson.types.ObjectId().toString());
       // hotel.setFlowId(hotel.getId()+"_"+hotel.getAgentId()+"_"+hotel.getHotelCode());
        Map<String, String> headerMap = RequestUtils.getHeaders(request);
        String agentId =headerMap.getOrDefault("agentid",null);
        if(StringUtils.isNotEmpty(agentId)) {
            hotel.setAgentId(agentId);
            Optional<Hotel> hotelExist = hotelRepository.findByAgentIdAndHotelCode(hotel.getAgentId(), hotel.getHotelCode());
            if (!hotelExist.isPresent()) {
                Hotel response = hotelRepository.save(hotel);
                HttpHeaders headers = new HttpHeaders();
                headers.set("HotelId", hotel.getId());
                headers.set("HotelCode", hotel.getHotelCode());
                headers.set("FlowId", hotel.getFlowId());
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successful", response));
                //return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successfull", response), HttpStatus.OK);
            } else {
                throw new B2bHotelException("HotelCode: (" + hotel.getHotelCode() + ") already exist for agentId: (" + hotel.getAgentId() + ") ,You can't add only edit is possible");
            }
        }
        else {
            throw new B2bHotelException("Agent Id not present in Request Headers");

        }



    }

    @Override
    public ResponseEntity<BaseResponse<List<Hotel>>> getAllHotels() {
      List<Hotel> response = hotelRepository.findAll();
        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfull",response),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<Room>>  addRoom(String hotelId, Room room) {
       // validateRoomName(room.getRoomName());
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (hotelOptional.isPresent()) {
           /* room.setHotelCode(hotelId); // Set the hotel reference*/
            Room response =roomRepository.save(room);
            return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(),HttpStatus.OK.value(), "Request is successfull",response),HttpStatus.OK);
        }
        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.NOT_FOUND.name(),HttpStatus.NOT_FOUND.value(), "Hotel ID Not found",null),HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<BaseResponse<Hotel>> getHotelByAgentIdAndHotelCode(String hotelCode) {
        Map<String, String> headerMap = RequestUtils.getHeaders(request);
        String agentId =headerMap.getOrDefault("agentid",null);
        if(StringUtils.isNotEmpty(agentId)) {
            Optional<Hotel> response = hotelRepository.findByAgentIdAndHotelCode(agentId, hotelCode);
            if (response.isPresent()) {

                HttpHeaders headers = new HttpHeaders();
                headers.set("HotelId", response.get().getId());
                headers.set("HotelCode", response.get().getHotelCode());
                headers.set("FlowId", response.get().getFlowId());
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successful", response.get()));
               // return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Request is successfull", response.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.NOT_FOUND.name(),
                         HttpStatus.NOT_FOUND.value(),
                        "HotelDetails are not available for this Agent: ( "+agentId + " ) and hotelCode: ( "+ hotelCode+ " )",
                        null), HttpStatus.NOT_FOUND);

            }
        }
        else {
            throw new B2bHotelException("Agent Id not present in Request Headers");
        }
    }

}
