package com.b2b.hotel.in.dto.season;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "seasons")
public class Season {
    @Id
    private String id;
    private String flowId;
    private String seasonName;
    private LocalDate seasonStartDate;
    private LocalDate seasonEndDate;
    private List<RoomIdActive> roomSelected;     // map of room IDs and room name associated with the season
}
