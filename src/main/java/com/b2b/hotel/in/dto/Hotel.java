package com.b2b.hotel.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "hotels")
@AllArgsConstructor
public class Hotel {
    @Id
    private String id;
    private String name;
    private String city;
    private String address;
    private double pricePerNight;
    private double rating;
    private int availableRooms;
    private boolean isBooked;
}
