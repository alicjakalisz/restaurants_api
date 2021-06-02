package com.alicja.restaurants.service;

import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.json_converter.JsonConverter;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RestaurantService {

    //id needs to be dynamic, this is an example

    static String searchListUrl = "https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJsclcktQEdkgRixfvZ2ewghM&key=&language=en";


    public RestaurantDto getRestaurantDtoById(long id) {
        JsonConverter jsonConverter = new JsonConverter();
        try {
           JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(searchListUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
