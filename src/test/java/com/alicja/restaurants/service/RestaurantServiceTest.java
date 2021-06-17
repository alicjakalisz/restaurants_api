package com.alicja.restaurants.service;


import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.exception.SearchException;
import com.alicja.restaurants.json_converter.JsonConverter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.*;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/google_map_search_response.json")));
        StringBuilder result = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        br.close();
        String urlContentString = result.toString();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(urlContentString).getAsJsonObject();
        try {
            when(converter.convertStringURLIntoJsonObject(any())).thenReturn(jsonObject);
        } catch (SearchException e) {

            e.printStackTrace();
        }

        List<ResearchResponseDto> outcome = service.getResearchResults("London", Optional.of("vegan"), Optional.of(500), Optional.of(5));

        assertEquals(20, outcome.size());
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
        String urlContentString = result.toString();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(urlContentString).getAsJsonObject();
        try {
            when(converter.convertStringURLIntoJsonObject(any())).thenReturn(jsonObject);
        } catch (SearchException e) {
            e.printStackTrace();
        }
        Optional<RestaurantDto> outcome = service.getRestaurantDtoById("ChIJsclcktQEdkgRixfvZ2ewghM");

        //TODO
        Optional<RestaurantDto> expected = Optional.of(new RestaurantDto("ChIJsclcktQEdkgRixfvZ2ewghM", "45,Lexington Street,Carnaby,London,Greater London,England,United Kingdom,W1F 9AN",
                "4.5", Optional.ofNullable(2), "\u003ca href=\"https://maps.google.com/maps/contrib/109852045475589675218\"\u003eMildreds Soho\u003c/a\u003e", "http://www.mildreds.co.uk/", "4.5",
                "020 7494 1634",
                "I visited for my birthday yesterday and had a wonderful time." +
                        " The food and cocktails were perfect. We were served by Kate, who was great fun, professional and just all round delightful." +
                        " I loved every moment. " +
                        "Even with current restrictions in the hospitality industry Mildred’s have proven that celebrations and great dining is still very possible. " +
                        "Thank you for a great meal.,I recently had lunch at Mildred’s and had a fantastic experience. " +
                        "Really nice atmosphere and great service.  I ordered the Pad Thai which was delicious; " +
                        "fresh and healthy ingredients with just the right amount of chilli." +
                        " For a drink I ordered the iced tea with pineapple and green tea which was really refreshing. " +
                        "My friend ordered one of the ‘bowls’ which he said was delicious too. I’d highly recommend a visit to Mildred’s Soho." +
                        ",Haven't been here in a few years and my god has vegan food improved. Absolutely stunning." +
                        " The most well balanced meal I've had in London, and so so so fresh and bursting with nutrients. " +
                        "I'm not a vegan myself but I love this place,Great customer service and tasty vegan food!! " +
                        "We hadn’t booked but the manager squeezed us in, for which we are so grateful for as the food was amazing. " +
                        "Also great value for money. We will definitely be coming back!!," +
                        "The staff here are going above and beyond to make dining with Mildreds still a glorious experience within the covid parameters. " +
                        "They all wear masks, abide by distancing regulations, and are still able to give fantastic menu and pairing suggestions." +
                        " The food is top notch, with many items complimenting each other and the wine list. " +
                        "They are currently operating card only, and tips do go to the staff. Definitely coming back soon"));
        assertEquals(expected, outcome);
    }

    @Test
    public void testThatWhenTheIdDoesNotExistThenReturnsAnEmptyOptionalRestaurant() throws IOException, SearchException {
        Optional<RestaurantDto> expected = Optional.empty();
        when(converter.convertStringURLIntoJsonObject(any())).thenThrow(new SearchException("The id sfdadfsafds does not exist."));
        Optional<RestaurantDto> outcome = service.getRestaurantDtoById("sfdadfsafds");//should be Optional.empty

        assertEquals(expected, outcome);
    }
}
