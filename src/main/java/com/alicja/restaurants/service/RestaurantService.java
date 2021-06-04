package com.alicja.restaurants.service;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.json_converter.JsonConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {


    static String prefixURLDetails = "https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJsclcktQEdkgRixfvZ2ewghM&language=en&key=";

    static String prefixURLSearch = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+";

    private JsonConverter jsonConverter;

    @Autowired
    public RestaurantService(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    //FIRST METHOD

    public RestaurantDto getRestaurantDtoById(String id) {
        RestaurantDto restaurantDto = new RestaurantDto();
        String apiKey = "AIzaSyCDmH0lnztl9AHa1bjP11bEQzlh2vRYH5A";
        String URLDetaiñs = prefixURLDetails+apiKey;

        try {
           JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(URLDetaiñs);
           //String id, String address, String rating, int price_level, String photo,
            // String website, String user_rating_total, String phone_number, String comments

           //TODO get object by ID from JsonObject
            JsonObject result = jsonObject.get("result").getAsJsonObject();
           String name =  result.get("name").getAsString();
           JsonArray addressComponentsList = result.get("address_components").getAsJsonArray();
           StringBuilder address = new StringBuilder();
            for (JsonElement element :addressComponentsList) {
                address.append(element.getAsJsonObject().get("long_name").getAsString());
            }
            String addressString = address.toString();
            String rating = result.get("rating").getAsString();
            int price_level = result.get("price_level").getAsInt();
            String photo = result.get("photos").getAsJsonArray().get(0).getAsJsonObject().get("html_attributions").getAsString();
            String website = result.get("website").getAsString();
            String ratingTotal = result.get("rating").getAsString();
            String phone_numer= result.get("formatted_phone_number").getAsString();
            String comments;
            ArrayList<String> listdata = new ArrayList<String>();







        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurantDto;
    }
    // SECOND METHOD
    public List<ResearchResponseDto> getResearchResults(String location, Optional<String> cuisine, Optional<Integer> radius, Optional<Integer> rating) {
        String searchListUrl = prefixURLSearch + "+" + location + "+" + cuisine +"&radius="+radius+"&alt=json&key=";
        String apiKey = "AIzaSyCDmH0lnztl9AHa1bjP11bEQzlh2vRYH5A";
        searchListUrl = searchListUrl+apiKey;
        List<ResearchResponseDto> researchResponseDtosList = new ArrayList<>();

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

            researchResponseDtosList.add(new ResearchResponseDto(placeId,name,address,rating1,Integer.parseInt(price_level),photo));
        });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return researchResponseDtosList;
    }
}
