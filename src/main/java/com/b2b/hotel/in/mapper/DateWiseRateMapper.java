package com.b2b.hotel.in.mapper;

import com.b2b.hotel.in.dto.rate.DatewiseRate;
import com.b2b.hotel.in.dto.rate.DatewiseRateIntoDatabase;
import com.b2b.hotel.in.dto.rate.RoomPricingData;
import com.b2b.hotel.in.dto.rate.RoomWiseDateWiseRate;
import com.b2b.hotel.in.dto.rate.enums.InclusionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
@Service
public interface DateWiseRateMapper {

    @Mapping(target = "agentId" ,source = "agentId")
    @Mapping(target = "id", ignore = true) // MongoDB will auto-generate the ID
    @Mapping(target = "roomRates", source = ".", qualifiedByName = "mapRoomRates")
    DatewiseRateIntoDatabase dateWiseRateToDatewiseRateIntoDatabase(DatewiseRate datewiseRate);



    @Named("mapRoomRates")
    default Map<String, RoomWiseDateWiseRate> mapRoomRates(DatewiseRate datewiseRate) {
        if (datewiseRate == null) {
            return new HashMap<>(); // Return an empty map if input is null
        }

        Map<String, RoomWiseDateWiseRate> roomRateMap = new HashMap<>();
        RoomWiseDateWiseRate roomRate = new RoomWiseDateWiseRate();

        // Dynamically map values from DatewiseRate to RoomWiseDateWiseRate
        roomRate.setRoomType(datewiseRate.getRoomType());
        roomRate.setMaxPax(datewiseRate.getMaxPax());
        roomRate.setChildCompAge(datewiseRate.getChildCompAge());
        roomRate.setNoOfRooms(datewiseRate.getNoOfRooms());
        roomRate.setInclusion(datewiseRate.getInclusion());
        roomRate.setCancellationPolicy(datewiseRate.getCancellationPolicy());
        roomRate.setCancellationBeforeDays(datewiseRate.getCancellationBeforeDays());

        // Mapping RoomPricingData to Map<Date, RoomPricingData>
        Map<LocalDate, RoomPricingData> pricingDataMap = new HashMap<>();
        if (datewiseRate.getRoomPricingDataList() != null) {
            for (RoomPricingData data : datewiseRate.getRoomPricingDataList()) {
                System.out.println(data.getPricingDate());
                pricingDataMap.put(data.getPricingDate(), data); // Map each RoomPricingData by pricingDate
            }
        }

        // Create InclusionType-based inclusion-wise rates
        Map<InclusionType, Map<LocalDate, RoomPricingData>> inclusionWiseRates = new HashMap<>();
        if (datewiseRate.getInclusion() != null) {
            inclusionWiseRates.put(datewiseRate.getInclusion(), pricingDataMap);
        }

        // Set the inclusion-wise rates to RoomWiseDateWiseRate
        roomRate.setRoomRateInclusionWise(inclusionWiseRates);

        // Put roomRate in the map with roomType as the key
        if (datewiseRate.getRoomType() != null) {
            roomRateMap.put(datewiseRate.getRoomType(), roomRate);
        }

        return roomRateMap;
    }

}
