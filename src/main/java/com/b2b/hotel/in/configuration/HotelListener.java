package com.b2b.hotel.in.configuration;

import com.b2b.hotel.in.dto.hotel.Hotel;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

@Component
public class HotelListener implements BeforeConvertCallback<Hotel> {

    @Override
    public Hotel onBeforeConvert(Hotel hotel, String collection) {
        // If id is null, MongoDB will generate it upon saving
        if (hotel.getId() == null) {
            // MongoDB generates id automatically, but you can simulate it if necessary before insert
            hotel.setId(new org.bson.types.ObjectId().toString());
        }

        // Generate flowId as id + agentId + hotelCode
        hotel.setFlowId(hotel.getId() + "_" + hotel.getAgentId() + "_" + hotel.getHotelCode());
        
        return hotel;
    }
}
