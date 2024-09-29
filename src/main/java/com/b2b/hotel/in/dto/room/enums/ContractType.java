package com.b2b.hotel.in.dto.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ContractType {
    NONE("None"),
    STATIC_DIRECT("Static Direct"),
    STATIC_CHANNEL_MANAGER("Static Channel Manager"),
    BAR_DIRECT("Bar Direct"),
    BAR_CHANNEL_MANAGER("Bar Channel Manager");


    @Getter
    private final String value;

    ContractType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }
}
