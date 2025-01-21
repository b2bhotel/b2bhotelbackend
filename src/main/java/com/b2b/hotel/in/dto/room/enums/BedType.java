package com.b2b.hotel.in.dto.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum BedType {
    ANY("Any"),
    SINGLE_BED("Single Bed"),
    DOUBLE_BED("Double Bed"),
    TRIPLE_BED("Triple Bed"),
    QUAD("Quad"),

    TWIN_BED("Twin Bed"),
    THREE_BEDROOM("Three Bedroom"),
    FOUR_BEDROOM("Four Bedroom"),
    ONE_BEDROOM("One Bedroom"),
    TWO_BEDROOM("Two Bedroom");

    @Getter
    private final String value;

    BedType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
}
