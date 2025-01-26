package com.b2b.hotel.in.repository;


import com.b2b.hotel.in.dto.hotel.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends MongoRepository<Hotel, String> {
    Optional<Hotel> findByAgentIdAndHotelCode(String agentId, String hotelCode);
    @Query("{ 'address.city' : ?0, 'address.country' : ?1 }")  // Query by both city and country
    Optional<List<Hotel>> findByCityAndCountry(String city, String country);
}
