package com.alicja.restaurants.service;


import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.json_converter.JsonConverter;
import com.alicja.restaurants.service.RestaurantService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
public class RestaurantServiceTest {


    @Mock
    JsonConverter converter;

    @InjectMocks
    RestaurantService service;

    @Test
    public void testShouldReturnAListofRestaurants() throws IOException {
        //You need to read as a String the file you placed in src/test/resources with the response from google.


        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/google_map_search_response.json")));
        StringBuilder result = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        br.close();
        //json read into the string from resource file (you can do it directly from URL with URL class, then url.openconnection, conn. setGetrequest, conn.getInputStream into Buffer
        String urlContentString = result.toString();
         //Fill the response from the Buffer into a String
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(urlContentString).getAsJsonObject();


        when(converter.convertStringURLIntoJsonObject(any())).thenReturn(jsonObject);
        //This method is what you have to test
       List<ResearchResponseDto> outcome =  service.getResearchResults("London", Optional.of("vegan"),Optional.of(500),Optional.of(5));
      //  TODO
       List<ResearchResponseDto> expected = new ArrayList<>();

       assertEquals(expected,outcome);
      //  Now you have a mock for the jsonConverter that is not really connecting to a URL. Any call to any URL always returns the result from the src/test/resources/google_response.json file.
    }

    @Test
    public void testShouldReturnDetailedDtoById() throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/google_map_detail_by_id.json")));
        StringBuilder result = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        br.close();
        //json read into the string from resource file (you can do it directly from URL with URL class, then url.openconnection, conn. setGetrequest, conn.getInputStream into Buffer
        String urlContentString = result.toString();
        //Fill the response from the Buffer into a String
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(urlContentString).getAsJsonObject();



        when(converter.convertStringURLIntoJsonObject(any())).thenReturn(jsonObject);
        //String id, String address, String rating, int price_level, String photo, String website, String user_rating_total, String phone_number, String comments
        RestaurantDto outcome = service.getRestaurantDtoById("ChIJsclcktQEdkgRixfvZ2ewghM");

        //TODO
        RestaurantDto expected = new RestaurantDto();
        assertEquals(expected,outcome);

    }
}
