package com.b2b.hotel.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
@AllArgsConstructor
public class Review {
    @Id
    private String id;
    private String hotelId;  // This is a reference to the Hotel
    private String user;
    private int rating;
    private String comment;
}
