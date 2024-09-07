package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    List<Hotel> findByCity(String city);
    // Custom query methods can be defined here
}
