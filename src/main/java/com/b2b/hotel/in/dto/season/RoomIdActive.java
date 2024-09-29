package com.b2b.hotel.in.dto.season;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RoomIdActive {
    private String roomId;
    private String roomName;
    @Builder.Default
    private Boolean status = Boolean.FALSE; // Default value set to false;
}
