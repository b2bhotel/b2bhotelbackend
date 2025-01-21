package com.b2b.hotel.in.dto.room;

import com.b2b.hotel.in.dto.Image;
import com.b2b.hotel.in.dto.room.enums.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Document(collection = "room")  // Collection for storing room information
public class Room {

    @Id
    private String id;  // MongoDB document id
    private String agentId;
    private String hotelId;
    private String hotelCode;
    private String flowId;
    private String propertyName;
    private String roomName;
    private Boolean childAsAdult;
    private PriceType priceType;  // e.g., Per Room //enum
    private ContractType contractType;  // Static Direct //enum
    private BedType bedType;  // e.g., Quad //enum
    private String ratePlanCode;
    // Basic Occupancy Fields
    private Occupancy basicOccupancy;
    private Map<OccupancyType,AdditionalOccupancy> additionalOccupancies;  //AdditionalOccupancy1,{Adult :1,Child:1} //enum

    // Extra Bed Information
    private ExtraBedOccupancy extraBedOccupancy;

    // Facilities
    private Map<RoomFacility,Boolean> roomFacilities; //enum

    // Photos (URLs or paths to image files)
    private List<Image> roomImages;

    // Room Description
    private String roomDescription;

    // Rate Inclusions
    private String rateInclusions;

    private double allocationExhaustThreshold;  // percentage value (0-100)  // will use later

    // Additional fields can be added based on further requirements

}