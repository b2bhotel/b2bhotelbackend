package com.b2b.hotel.in.dto.rate.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum InclusionType {
    ROOM_ONLY("Room Only"),
    CPAI("CPAI"),
    MAPAI("MAPAI"),
    APAI("APAI");

    @Getter
    private final String value;

    InclusionType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

}

//EP (European Plan)	Room only	No meals included. Guests pay for all meals separately.
//EP (European Plan)	Room only	No meals included. Guests pay for all meals separately.
//MAPAI (Modified American Plan)	Room + Breakfast + One Meal	Includes breakfast and one main meal (lunch or dinner) of choice.
//APAI (American Plan)	Room + Breakfast + All Meals	Includes breakfast, lunch, and dinner in the package.
//AI (All Inclusive)	Room + All Meals + Snacks + Drinks	Includes all meals, snacks, and beverages (often alcoholic and non-alcoholic).
//FP (Full Board Plan)	Room + Breakfast + Lunch + Dinner	Similar to APAI but does not include snacks or beverages.
//Half Board (HB)	Room + Breakfast + Dinner	Includes breakfast and dinner but excludes lunch.
