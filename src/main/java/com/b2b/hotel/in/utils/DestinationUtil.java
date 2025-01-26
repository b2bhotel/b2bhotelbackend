package com.b2b.hotel.in.utils;

import com.b2b.hotel.in.dto.hotel.Hotel;
import com.b2b.hotel.in.exception.B2bHotelException;
import com.b2b.hotel.in.repository.HotelRepository;

import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@UtilityClass
public class DestinationUtil {

    public List<String> getFlowIdsByDestination(String destination, HotelRepository hotelRepository) {
        // Extract city and country from the comma-separated string
        Map<String, String> location = extractCityAndCountry(destination);

        String city = location.get("city");
        String country = location.get("country");

        // Find hotels based on city and country
        List<Hotel> hotels = hotelRepository.findByCityAndCountry(city, country).orElse(List.of());

        // Extract and return the flowIds from the matched hotels
        return hotels.stream()
            .map(Hotel::getFlowId).toList();

    }

    // Helper method to split the destination string and extract city and country
    private Map<String, String> extractCityAndCountry(String cityAndCountry) {
        String[] parts = cityAndCountry.split(",", 2);

        if (parts.length != 2) {
            throw new B2bHotelException("destination must be in 'city, country' format.");
        }

        String city = parts[0].trim();
        String country = parts[1].trim();

        return Map.of("city", city, "country", country);
    }
}
