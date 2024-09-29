package com.b2b.hotel.in.dto.room;

import com.b2b.hotel.in.dto.room.enums.AllowAdultExtrabedOn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ExtraBedOccupancy {
    private boolean adultExtraBedAllowed;
    private int numberOfAdultExtraBeds;
    private Map<AllowAdultExtrabedOn,Boolean> allowAdultExtraBedsOn; //enum
    private boolean childExtraBedAllowed;
    private int numberOfChildExtraBeds;
    private String allowChildExtraBedsOn;//enum
    private String extraBedType;  //enum
}