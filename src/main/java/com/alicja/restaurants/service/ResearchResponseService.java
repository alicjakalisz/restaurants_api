package com.alicja.restaurants.service;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.json_converter.JsonConverter;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ResearchResponseService {

    //query parameters need to be dynamic, this is an example, change the https with regex

  //  static String searchListUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+london+vegan&radius=2000&key=&alt=json";
    static String prefixURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+";


    private JsonConverter jsonConverter;

    @Autowired
    public ResearchResponseService(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    public List<ResearchResponseDto> getResearchResults(String location, Optional<String> cuisine, Optional<Integer> radius, Optional<Integer> rating) {
        String searchListUrl = prefixURL + "+" + location + "+" + cuisine +"&radius="+radius+"&alt=json&key=";
        String apiKey = "AIzaSyCDmH0lnztl9AHa1bjP11bEQzlh2vRYH5A";
        searchListUrl = searchListUrl+apiKey;


        try {
           JsonObject jsonObject = jsonConverter.convertStringURLIntoJsonObject(searchListUrl);
            System.out.println(jsonObject);
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
