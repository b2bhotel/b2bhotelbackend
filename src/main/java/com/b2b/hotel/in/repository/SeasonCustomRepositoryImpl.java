package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.season.RoomIdActive;
import com.b2b.hotel.in.dto.season.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
public class SeasonCustomRepositoryImpl implements SeasonCustomRepository {


    private final MongoTemplate mongoTemplate;

    @Override
    public boolean existsByFlowIdAndRoomDetailsAndDateOverlap(String flowId, List<RoomIdActive> roomDetails, LocalDate startDate, LocalDate endDate) {
        MatchOperation matchFlowId = match(Criteria.where("flowId").is(flowId));

        MatchOperation matchRoomDetails = match(Criteria.where("roomDetails.roomId").in(
                roomDetails.stream().map(RoomIdActive::getRoomId).toArray(String[]::new)
        ));

        MatchOperation matchOverlappingDates = match(new Criteria().orOperator(
                Criteria.where("seasonStartDate").lte(endDate).and("seasonEndDate").gte(startDate),
                Criteria.where("seasonStartDate").gte(startDate).and("seasonStartDate").lte(endDate)
        ));

        Aggregation aggregation = newAggregation(matchFlowId, matchRoomDetails, matchOverlappingDates);

        AggregationResults<Season> results = mongoTemplate.aggregate(aggregation, "seasons", Season.class);

        return results.getMappedResults().size() > 0;
    }
}
