package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.Hotel;
import com.b2b.hotel.in.dto.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    // Custom query methods can be defined here
}
