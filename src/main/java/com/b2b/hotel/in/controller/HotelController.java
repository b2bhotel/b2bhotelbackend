package com.b2b.hotel.in.controller;

import com.b2b.hotel.in.dto.Hotel;
import com.b2b.hotel.in.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels() {
        System.out.println("getAllHotels");
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Optional<Hotel> getHotelById(@PathVariable String id) {
        return hotelService.getHotelById(id);
    }

    // Other endpoints
    @PostMapping("/addhotels")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel); // This will handle POST requests to add a new hotel
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/city/{cityName}")
    public List<Hotel> getHotelsByCity(@PathVariable String cityName) {
        return hotelService.getHotelsByCity(cityName);

    }
}
