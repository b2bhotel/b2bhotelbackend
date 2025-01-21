package com.b2b.hotel.in.configuration;

import com.b2b.hotel.in.dto.*;
import com.b2b.hotel.in.repository.HotelRepository;
import com.b2b.hotel.in.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

   /* @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Clear existing data
            hotelRepository.deleteAll();
            roomRepository.deleteAll();

            // Create and save hotels
            List<Hotel> hotels = Arrays.asList(
                createHotel("1", "ChainName1", "HotelName1", "Type1", "USD", "GMT"),
                createHotel("2", "ChainName2", "HotelName2", "Type2", "EUR", "CET"),
                createHotel("3", "ChainName3", "HotelName3", "Type3", "GBP", "BST"),
                createHotel("4", "ChainName4", "HotelName4", "Type4", "JPY", "JST"),
                createHotel("5", "ChainName5", "HotelName5", "Type5", "AUD", "AEST"),
                createHotel("6", "ChainName6", "HotelName6", "Type6", "CAD", "EST")
            );
            hotelRepository.saveAll(hotels);

            // Create and save rooms
            List<Room> rooms = Arrays.asList(
                createRoom("1", hotels.get(3).getId(), "Deluxe Room 1", "King", "Standard", 2, 1),
                createRoom("2", "1", "Superior Room 1", "Queen", "Standard", 2, 1),
                createRoom("3", "2", "Suite Room 1", "King", "Premium", 3, 2),
                createRoom("4", "2", "Economy Room 1", "Single", "Economy", 1, 0),
                createRoom("5", "3", "Deluxe Room 2", "King", "Standard", 2, 1),
                createRoom("6", "3", "Superior Room 2", "Queen", "Standard", 2, 1),
                createRoom("7", "4", "Suite Room 2", "King", "Premium", 3, 2),
                createRoom("8", "4", "Economy Room 2", "Single", "Economy", 1, 0),
                createRoom("9", "5", "Deluxe Room 3", "King", "Standard", 2, 1),
                createRoom("10", "6", "Suite Room 3", "King", "Premium", 3, 2)
            );
            roomRepository.saveAll(rooms);

            System.out.println("Data initialized successfully");
        };
    }

    private Hotel createHotel(String id, String chainName, String propertyName, String propertyType, String currencyCode, String timeZone) {
        Address address = new Address("123 Main St", "Apt 4B", "12345", "Cityville", "Countryland");
        ContactDetails contactDetails = new ContactDetails("123-456-7890", "123-456-7891", "contact@example.com");
        ExtraBedOccupancy extraBedOccupancy = new ExtraBedOccupancy(true, 2);
        Location location = new Location("40.7128", "-74.0060", "http://maps.example.com");
        Image image = new Image("http://example.com/image.jpg", "Example Image");
        List<String> propertyWeekends = Arrays.asList("Saturday", "Sunday");

        return new Hotel(
            id,
            chainName,
            propertyName,
            propertyType,
            address,
            contactDetails,
            "H" + id,
            "5",
            currencyCode,
            timeZone,
            "2024-09-15T00:00:00Z",
            Arrays.asList(image),
            location,
            "A description of " + propertyName,
            "Internal remarks for " + propertyName,
            propertyWeekends
        );
    }

    private Room createRoom(String id, String hotelId, String roomName, String bedType, String priceType, int adults, int children) {
        ExtraBedOccupancy extraBedOccupancy = new ExtraBedOccupancy(true, 2);
        Occupancy occupancy = new Occupancy(adults, children, 0, extraBedOccupancy);
        List<String> facilities = Arrays.asList("WiFi", "TV");

        return new Room(
            id,
            hotelId,
            roomName,
            bedType,
            priceType,
            occupancy,
            facilities,
            "RP" + id,
            "Breakfast included",
            "A description of " + roomName
        );
    }*/
}
