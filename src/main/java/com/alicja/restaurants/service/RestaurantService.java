package com.alicja.restaurants.service;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.json_converter.JsonConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    //id needs to be dynamic, this is an example

    static String prefixURLDetails = "https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJsclcktQEdkgRixfvZ2ewghM&key=&language=en";

    //  static String searchListUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+london+vegan&radius=2000&key=&alt=json";
    static String prefixURLSearch = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+";



    private JsonConverter jsonConverter;

    @Autowired
    public RestaurantService(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    //FIRST METHOD

    public RestaurantDto getRestaurantDtoById(long id) {
        try {
           JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(prefixURLDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // SECOND METHOD
    public List<ResearchResponseDto> getResearchResults(String location, Optional<String> cuisine, Optional<Integer> radius, Optional<Integer> rating) {
        String searchListUrl = prefixURLSearch + "+" + location + "+" + cuisine +"&radius="+radius+"&alt=json&key=";
        String apiKey = "AIzaSyCDmH0lnztl9AHa1bjP11bEQzlh2vRYH5A";
        searchListUrl = searchListUrl+apiKey;


        try {
            JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(searchListUrl);
            System.out.println(jsonObject);
            //HERE YOU HAVE TO WRITE THE LOGIC, that converts the json object into a List<researchResponseDto>
            JsonArray results = jsonObject.get("results").getAsJsonArray();
            results.forEach(result ->{
            String placeId = result.getAsJsonObject().get("place_id").getAsString();
            String name = result.getAsJsonObject().get("name").getAsString();
            String address = result.getAsJsonObject().get("formatted_address").getAsString();
            String rating1 = result.getAsJsonObject().get("rating").getAsString();
            String price_level = result.getAsJsonObject().get("price_level").getAsString();
            String photo = result.getAsJsonObject().get("photos").getAsJsonArray().get(0).getAsJsonObject().get("html_attributions").getAsString();



        });
            jsonObject.get("results").getAsJsonObject();



        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        * Http Response
	[{

		"id"
		"name"
		"address"
		"rating"
		"price_level"
		"photo"
	}]*/
        return null;
    }
}
