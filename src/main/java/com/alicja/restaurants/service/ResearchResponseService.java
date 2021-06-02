package com.alicja.restaurants.service;

import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.json_converter.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ResearchResponseService {

    //query parameters need to be dynamic, this is an example, change the https with regex

    static String searchListUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+london+vegan&radius=2000&key=AIzaSyD1R1PC8TDYF40HGb3JZJlWzZ8r0v3tNUw&alt=json";




    public List<ResearchResponseDto> getResearchResults(String location, Optional<String> cuisine, Optional<Integer> radius, Optional<Integer> rating) {
        JsonConverter jsonConverter = new JsonConverter();
        try {
            jsonConverter.convertStringURLIntoJsonObject(searchListUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
