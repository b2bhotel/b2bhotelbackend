package com.b2b.hotel.in.dto.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum BoardBasisForFirstChildFree {
    RO("RO"),
    BB("BB"),
    HB("HB"),
    FB("FB");

    @Getter
    private final String value;

    BoardBasisForFirstChildFree(String value) {
        this.value = value;
    }@JsonValue
    public String getValue() {
        return value;
    }
}
