package com.alicja.restaurants.service;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.exception.SearchException;
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


    static String prefixURLDetails = "https://maps.googleapis.com/maps/api/place/details/json?language=en&place_id=";

    static String prefixURLSearch = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+";

    private final JsonConverter jsonConverter;

    @Autowired
    public RestaurantService(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    //FIRST METHOD

    //todo returns optional //correct mock to return opiotional as well
    public Optional<RestaurantDto> getRestaurantDtoById(String id) {
        RestaurantDto restaurantDto = new RestaurantDto();
        String apiKey = "AIzaSyCDmH0lnztl9AHa1bjP11bEQzlh2vRYH5A";
        String urlDetails = prefixURLDetails + id + "&key=" + apiKey;

        try {
            JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(urlDetails);
            //TODO get object by ID from JsonObject
            JsonObject result = jsonObject.get("result").getAsJsonObject();
            String name = result.get("name").getAsString();
            JsonArray addressComponentsList = result.get("address_components").getAsJsonArray();
            StringBuilder address = new StringBuilder();
            for (JsonElement element : addressComponentsList) {
                address.append(element.getAsJsonObject().get("long_name").getAsString()).append(" ");
            }
            String addressString = address.toString();
            String addressResult = String.join(",",addressString);
            String rating = result.get("rating").getAsString();

            Optional<Integer> priceLevel = Optional.empty();
            if (result.get("price_level") != null) {
                priceLevel =Optional.of(result.get("price_level").getAsInt());
            }

            String photo = result.get("photos").getAsJsonArray().get(0).getAsJsonObject().get("html_attributions").getAsString();
            String website = result.get("website").getAsString();
            String ratingTotal = result.get("rating").getAsString();
            String phoneNumber = result.get("formatted_phone_number").getAsString();
            StringBuilder comments = new StringBuilder();
            JsonArray reviews = result.get("reviews").getAsJsonArray();
            for (JsonElement e : reviews) {
                comments.append(e.getAsJsonObject().get("text").getAsString());
                comments.append(" ");
            }
            String commentsString = String.join(",", comments.toString());


            restaurantDto.setId(id);
            restaurantDto.setAddress(addressResult);
            restaurantDto.setRating(rating);
            restaurantDto.setPriceLevel(priceLevel);
            restaurantDto.setPhoto(photo);
            restaurantDto.setWebsite(website);
            restaurantDto.setUserRatingTotal(rating);
            restaurantDto.setPhoneNumber(phoneNumber);
            restaurantDto.setComments(commentsString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SearchException e) {
            return Optional.empty();
        }
        return Optional.of(restaurantDto);
    }

    // SECOND METHOD
    public List<ResearchResponseDto> getResearchResults(String location, Optional<String> cuisine, Optional<Integer> radius, Optional<Integer> rating) {
        String searchListUrl = prefixURLSearch + "+" + location + "+" + cuisine + "&radius=" + radius + "&alt=json&key=";
        String apiKey = "AIzaSyCDmH0lnztl9AHa1bjP11bEQzlh2vRYH5A";
        searchListUrl = searchListUrl + apiKey;
        List<ResearchResponseDto> researchResponseDtosList = new ArrayList<>();

        try {
            JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(searchListUrl);
            System.out.println(jsonObject);
            JsonArray results = jsonObject.get("results").getAsJsonArray();
            results.forEach(result -> {
                JsonObject resultJsonObj=result.getAsJsonObject();
                String placeId = resultJsonObj.get("place_id").getAsString();
                String name = resultJsonObj.get("name").getAsString();
                String address = resultJsonObj.get("formatted_address").getAsString();
                String rating1 = resultJsonObj.get("rating").getAsString();
                String price_level = resultJsonObj.get("price_level").getAsString();
                String photo = resultJsonObj.get("photos").getAsJsonArray().get(0).getAsJsonObject().get("html_attributions").getAsString();

                researchResponseDtosList.add(new ResearchResponseDto(placeId, name, address, rating1, Integer.parseInt(price_level), photo));
            });

        } catch (IOException | SearchException e) {
            e.printStackTrace();
        }

        return researchResponseDtosList;
    }
}
