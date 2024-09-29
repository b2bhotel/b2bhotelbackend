package com.b2b.hotel.in.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private String longitude;
    private String latitude;
    private String mapLink;
}
