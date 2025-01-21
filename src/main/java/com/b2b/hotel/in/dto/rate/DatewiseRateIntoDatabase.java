package com.b2b.hotel.in.dto.rate;

import com.b2b.hotel.in.dto.rate.enums.InclusionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class DatewiseRateIntoDatabase {
    @Id
    private String id;
    private String agentId;
    private String hotelId;
    private String hotelCode;
    private String flowId;
    private Map<String, RoomWiseDateWiseRate> roomRates;   //string would be RoomType i.e= deluxe , standard etc//private Map<String, Map<InclusionType, Map<Date, RoomWiseDateWiseRate>>> roomRateRoomTypeWise;
}
