package com.b2b.hotel.in.configuration;

import com.b2b.hotel.in.dto.Hotel;
import com.b2b.hotel.in.dto.Review;
import com.b2b.hotel.in.repository.HotelRepository;
import com.b2b.hotel.in.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Clear existing data
            hotelRepository.deleteAll();
            reviewRepository.deleteAll();

            // Insert predefined hotels
            hotelRepository.saveAll(List.of(
                new Hotel("1", "Grand Hotel", "New York", "123 Broadway Ave", 250, 4.5, 20, false),
                new Hotel("2", "Beachside Resort", "Miami", "456 Ocean Drive", 300, 4.7, 15, false),
                new Hotel("3", "Mountain Lodge", "Aspen", "789 Mountain Rd", 180, 4.2, 10, false)
            ));

            // Insert predefined reviews
            reviewRepository.saveAll(List.of(
                new Review("1", "1", "Emily", 5, "Excellent stay!"),
                new Review("2", "1", "JohnDoe", 4, "Very comfortable!"),
                new Review("3", "2", "AliceBrown", 5, "Fantastic location!"),
                new Review("4", "2", "BobJohnson", 4, "Great resort, but a bit noisy at night."),
                new Review("5", "3", "CharlieDavis", 4, "Cozy lodge with a beautiful view."),
                new Review("6", "3", "DianaMiller", 4, "Great for skiing, but limited dining options.")
            ));
        };
    }
}
