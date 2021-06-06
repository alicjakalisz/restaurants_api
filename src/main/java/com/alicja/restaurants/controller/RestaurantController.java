package com.alicja.restaurants.controller;


import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);



    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService =  restaurantService;
    }
    @GetMapping("restaurants/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantDtoById(@PathVariable(name = "id") String id){

       Optional<RestaurantDto> result = restaurantService.getRestaurantDtoById(id);
       // from optional to response entity depending if found or not
        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok().body(result.get());
        }
    }



}
