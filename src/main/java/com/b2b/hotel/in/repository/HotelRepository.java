package com.b2b.hotel.in.repository;


import com.b2b.hotel.in.dto.hotel.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HotelRepository extends MongoRepository<Hotel, String> {
    Optional<Hotel> findByAgentIdAndHotelCode(String agentId, String hotelCode);
}
