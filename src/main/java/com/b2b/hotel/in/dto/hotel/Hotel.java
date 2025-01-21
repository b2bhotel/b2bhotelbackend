package com.b2b.hotel.in.dto.hotel;

import com.b2b.hotel.in.dto.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "hotels")
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private String id;
    private String agentId;
    private String hotelCode;
    private String flowId;
    private String chainName;   //Assuming this as Agent name
    private String propertyName;
    private String propertyType;
    private Address address;
    private ContactDetails contactDetails;
    private String starRating;
    private String currencyCode;
    private String timeZone;
    private String releaseTime;  //checkout time
    private List<Image> images;
    private Location location;
    private String description;
    private String internalRemarks;
    private List<String> propertyWeekends; // List of weekend days

}
