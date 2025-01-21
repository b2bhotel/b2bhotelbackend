package com.b2b.hotel.in.dto.rate;

import com.b2b.hotel.in.dto.rate.enums.CancellationPolicyType;
import com.b2b.hotel.in.dto.rate.enums.InclusionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatewiseRate {

    private String agentId;
    private String hotelId;
    private String hotelCode;
    private String flowId;
    private String roomType;
    private String maxPax;
    private String childCompAge;
    private String noOfRooms;
    private InclusionType inclusion;   //enum.  RoomOnly , CPAI , MAPAI , APAI
    private CancellationPolicyType cancellationPolicy;  //enum
    private String cancellationBeforeDays;
    private List<RoomPricingData> roomPricingDataList;

}
