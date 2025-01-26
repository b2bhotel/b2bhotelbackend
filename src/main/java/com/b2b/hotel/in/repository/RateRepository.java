package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.rate.DatewiseRateIntoDatabase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RateRepository extends MongoRepository<DatewiseRateIntoDatabase,String> , RateCustomRepository {
    Optional<DatewiseRateIntoDatabase> findByFlowId(String flowId);
}
