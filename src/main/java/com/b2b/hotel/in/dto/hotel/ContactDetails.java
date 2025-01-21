package com.b2b.hotel.in.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDetails {
    private String phone;
    private String fax;
    private String email;
}
