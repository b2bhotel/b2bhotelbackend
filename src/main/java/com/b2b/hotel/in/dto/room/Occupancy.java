package com.b2b.hotel.in.dto.room;

import com.b2b.hotel.in.dto.room.enums.BoardBasisForFirstChildFree;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Occupancy {
    private int adults;  // Number of adults
    private int children;  // Number of children
    private Map<String,String> childrenAges;  // Ages of children key=child1,child2,child3(list for flexibility)
    private boolean firstChildFree;  // True if the first child is free
    private Map<BoardBasisForFirstChildFree,Boolean> boardBasisForFirstChildFree;  // RO, BB, HB, FB  //enum
    private boolean infantFree;
    private int selectNumberOfInfantsToFree;  // Number of free infants
}