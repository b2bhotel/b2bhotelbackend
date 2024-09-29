package com.b2b.hotel.in.dto.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomFacility {
    AIR_CONDITIONING("Air conditioning"),
    BATHROOM_AMENITIES("Bathroom amenities"),
    BREAKFAST("Breakfast"),
    BUFFET_BREAKFAST("Buffet breakfast"),
    COFFEE_TEA_MAKER("Coffee and Tea maker"),
    COLOR_TELEVISION("Color television"),
    COMPLIMENTARY_WATER("Complimentary bottle of mineral water"),
    DATA_PORT("Data port"),
    DRY_CLEANING("Dry Cleaning"),
    ELECTRONIC_SAFETY_LOCKER("Electronic Safety Locker"),
    FREE_CAR_PARKING("Free Car Parking"),
    HAIRDRYER("Hairdryer"),
    HEATING("Heating"),
    HIGH_SPEED_INTERNET("High speed internet connection"),
    INTERNATIONAL_DIRECT_DIALING("International direct dialing"),
    IRONING_BOARD("Ironing board"),
    IRONING_BOARD_ON_REQUEST("Ironing board on Request"),
    LCD_TV("LCD TV"),
    MINIBAR("Minibar"),
    NEWSPAPER("Newspaper"),
    REFRIGERATOR("Refrigerator"),
    ROOM_SERVICE("Room Service"),
    VCR("VCR"),
    WAKE_UP_CALLS("Wake up calls"),
    WIFI("Wi Fi");

    private final String value;

    RoomFacility(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
