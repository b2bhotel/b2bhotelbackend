package com.b2b.hotel.in.dto.rate.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum CancellationPolicyType {
    REFUNDABLE("Refundable"),
    NON_REFUNDABLE("Non Refundable");

    @Getter
    private final String value;

    CancellationPolicyType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }


}
