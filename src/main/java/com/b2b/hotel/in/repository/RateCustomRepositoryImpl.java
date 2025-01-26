package com.b2b.hotel.in.repository;

import com.b2b.hotel.in.dto.frontened.FrontenedRoomRateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class RateCustomRepositoryImpl implements RateCustomRepository{

    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public List<FrontenedRoomRateResponse> getFilteredRoomRates(List<String> flowIds, String startDate, String endDate) {
        AggregationOperation match = context -> new Document("$match", new Document("flowId", new Document("$in", flowIds)));

        Document projectStage = Document.parse("""
            {
                "$project": {
                    "flowId": 1,
                    "filteredRoomRates": {
                        "$map": {
                            "input": { "$objectToArray": "$roomRates" },
                            "as": "roomRate",
                            "in": {
                                "roomType": "$$roomRate.k",
                                "roomRateInclusionWise": {
                                    "$map": {
                                        "input": { "$objectToArray": "$$roomRate.v.roomRateInclusionWise" },
                                        "as": "rateInclusion",
                                        "in": {
                                            "inclusionType": "$$rateInclusion.k",
                                            "filteredRates": {
                                                "$filter": {
                                                    "input": { "$objectToArray": "$$rateInclusion.v" },
                                                    "as": "rate",
                                                    "cond": {
                                                        "$and": [
                                                            { "$gte": [ "$$rate.k", "%s" ] },
                                                            { "$lte": [ "$$rate.k", "%s" ] }
                                                        ]
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        """.formatted(startDate, endDate));

        AggregationOperation project = context -> projectStage;

        Aggregation aggregation = Aggregation.newAggregation(match, project);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "datewiseRateIntoDatabase", Document.class);

        // Convert Document results to FrontendRoomRateResponse
        return results.getMappedResults().stream()
            .map(doc -> objectMapper.convertValue(doc, FrontenedRoomRateResponse.class))
            .toList();
    }

}
