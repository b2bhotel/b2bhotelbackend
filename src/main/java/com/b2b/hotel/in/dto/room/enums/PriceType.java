package com.b2b.hotel.in.dto.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum PriceType {
    PER_ROOM("Per Room"),
    PER_UNIT("Per Unit");

    @Getter
    private final String value;

    PriceType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
}