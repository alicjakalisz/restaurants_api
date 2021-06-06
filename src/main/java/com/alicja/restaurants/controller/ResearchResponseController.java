package com.alicja.restaurants.controller;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.exception.SearchException;
import com.alicja.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
                                                                        @RequestParam(value = "rating", required = false) Integer rating) throws SearchException {
        List<ResearchResponseDto> result = restaurantService.getResearchResults(location, Optional.ofNullable(cuisine), Optional.ofNullable(radius), Optional.ofNullable(rating));
        return ResponseEntity.ok().body(result);

    }

    //If a required @RequestParam is not present in the request, Spring will throw a MissingServletRequestParameterException exception.
    // You can define an @ExceptionHandler in the same controller or in a @ControllerAdvice to handle that exception:

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
    }
}


