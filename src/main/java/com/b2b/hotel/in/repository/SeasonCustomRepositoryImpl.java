package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.season.RoomIdActive;
import com.b2b.hotel.in.dto.season.Season;
import com.b2b.hotel.in.exception.B2bHotelException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
public class SeasonCustomRepositoryImpl implements SeasonCustomRepository {


    private final MongoTemplate mongoTemplate;

    /*@Override
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
    }*/

    @Override
    public boolean existsByFlowIdAndRoomDetailsAndDateOverlap(String flowId, List<RoomIdActive> roomSelected, LocalDate startDate, LocalDate endDate) {

        // Step 1: Match flowId
        MatchOperation matchFlowId = Aggregation.match(Criteria.where("flowId").is(flowId));

        // Step 2: Filter active room IDs
        List<String> activeRoomIds = roomSelected.stream()
                .filter(RoomIdActive::getStatus) // Get rooms where status is true
                .map(RoomIdActive::getRoomId)
                .toList(); // Collecting as list of room IDs

        if (activeRoomIds.isEmpty()){
            throw new B2bHotelException("pls select atleast one room for season submission");
        }

        // Match only the active room IDs
        MatchOperation matchRoomDetails = Aggregation.match(Criteria.where("roomSelected.roomId").in(activeRoomIds));

        // Step 3: Check for overlapping dates
        MatchOperation matchOverlappingDates = Aggregation.match(new Criteria().orOperator(
                Criteria.where("seasonStartDate").lte(endDate).and("seasonEndDate").gte(startDate)  // seasonStartDate <= endDate and seasonEndDate >= startDate
        ));

        // Build the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                matchFlowId,          // Match flowId
                Aggregation.unwind("roomSelected"),  // Unwind the roomSelected array
                matchRoomDetails,     // Match active rooms
                matchOverlappingDates // Check overlapping dates
        );

        // Execute the aggregation
        AggregationResults<Season> results = mongoTemplate.aggregate(aggregation, "seasons", Season.class);

        // Check if any overlapping seasons exist
        return results.getMappedResults().size() > 0;
    }


}
