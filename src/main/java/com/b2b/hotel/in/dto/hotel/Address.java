package com.b2b.hotel.in.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String postalCode;
    private String city;
    private String country;
}
