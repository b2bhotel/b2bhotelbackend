package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.season.Season;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeasonRepository extends MongoRepository<Season, String> , SeasonCustomRepository{


}
