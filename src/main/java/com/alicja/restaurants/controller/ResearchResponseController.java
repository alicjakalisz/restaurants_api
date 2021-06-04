package com.alicja.restaurants.controller;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ResearchResponseController {

    private RestaurantService restaurantService;

    @Autowired
    public ResearchResponseController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    //*
    //
    //		"id"
    //		"name"
    //		"address"
    //		"rating"
    //		"price_level"
    //		"photo"*/

    ///**
    // parameters --> In spring these parameters are @RequestParam and java type is Optional
    //  	- location mandatory -->
    //  	- cuisine (optional)
    //  	- radius (meters) optional (if user does not introduce it, then default value of 5000 meters)
    //  	- rating  optional (integer) if they select 3, it will give results from 3 to 5*/

    //http://localhost:8080/restaurants/search?location=London&cuisine=vegan&radius=6000&rating=3
    @GetMapping("restaurants/search")
    public ResponseEntity<List<ResearchResponseDto>> getResearchResults(@RequestParam(value = "location", required = true) String location,
                                                        @RequestParam(value = "cuisine", required = false) String cuisine,
                                                        @RequestParam(value = "radius", defaultValue = "5000") Integer radius,
                                                        @RequestParam(value = "rating", required = false) Integer rating) {
        List<ResearchResponseDto> result = restaurantService.getResearchResults( location, Optional.ofNullable(cuisine),Optional.ofNullable(radius),Optional.ofNullable(rating));
        return ResponseEntity.ok().body(result);
    }

}
