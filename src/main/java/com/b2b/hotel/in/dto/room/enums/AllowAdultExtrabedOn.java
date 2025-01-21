package com.b2b.hotel.in.dto.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AllowAdultExtrabedOn {
    BASIC_OCCUPANCY("Basic Occupancy"),
    ADDITIONAL_OCCUPANCY_1("Additional Occupancy 1"),
    ADDITIONAL_OCCUPANCY_2("Additional Occupancy 2"),
    ADDITIONAL_OCCUPANCY_3("Additional Occupancy 3");


    @Getter
    private final String value;

    AllowAdultExtrabedOn(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
}
