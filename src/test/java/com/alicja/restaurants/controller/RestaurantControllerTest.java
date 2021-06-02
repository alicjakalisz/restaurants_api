package com.alicja.restaurants.controller;


import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void controllerShouldGetRestaurantById(){
        //mock input for the service
        //c RestaurantDto(long id, long address, String rating, int price_level, String photo,
        // String website, String user_rating_total, String phone_number, String comments)
        RestaurantDto restaurantDto = new RestaurantDto(1,1L,"3",3,"link photo","link website","5","1255456","good food");

       // restaurantService.getRestaurantDtoById()
    }



}
