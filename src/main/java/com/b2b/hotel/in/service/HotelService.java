package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.Hotel;
import com.b2b.hotel.in.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(String id) {
        return hotelRepository.findById(id);
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel); // This will insert the hotel into the MongoDB collection
    }
    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }
    // Other service methods@PostMapping

}
