package com.b2b.hotel.in.dto.rate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class RoomPricingData {
    private LocalDate pricingDate;
    private String pricingDay;
    private String roomRate;
//    private String roomRateEP;  //EP (European Plan)	Room only	No meals included. Guests pay for all meals separately.
//    private String roomRateCPAI;    //EP (European Plan)	Room only	No meals included. Guests pay for all meals separately.
//    private String roomRateMAPAI;   //MAPAI (Modified American Plan)	Room + Breakfast + One Meal	Includes breakfast and one main meal (lunch or dinner) of choice.
//    private String roomRateAPAI;    //APAI (American Plan)	Room + Breakfast + All Meals	Includes breakfast, lunch, and dinner in the package.
//    private String roomRateAI;  //AI (All Inclusive)	Room + All Meals + Snacks + Drinks	Includes all meals, snacks, and beverages (often alcoholic and non-alcoholic).
//    private String roomRateFP;  //FP (Full Board Plan)	Room + Breakfast + Lunch + Dinner	Similar to APAI but does not include snacks or beverages.
//    private String roomRateHB;  //Half Board (HB)	Room + Breakfast + Dinner	Includes breakfast and dinner but excludes lunch.
    private String extraBedRate;
    private String childwithBed;

}

