package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.login.LoginDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginRepository extends MongoRepository<LoginDetails,String> {
    Optional<LoginDetails> findByUserNameAndPassword(String userName, String password);
}
