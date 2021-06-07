package com.alicja.restaurants.controller;


import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.service.RestaurantService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void controllerShouldGetRestaurantById() throws Exception {
        RestaurantDto restaurantDto = new RestaurantDto("1", "blabla", "3", Optional.ofNullable(3), "link photo", "link website", "5", "1255456", "good food");


        when(restaurantService.getRestaurantDtoById("1")).thenReturn(Optional.of(restaurantDto));

        ResultActions resultActions = mockMvc.perform(get("/restaurants/1"));
        MvcResult mvcResult = resultActions.andReturn();
        String contentString = mvcResult.getResponse().getContentAsString();
        RestaurantDto outcome = objectMapper.readValue(contentString, new TypeReference<RestaurantDto>() {
        });
        assertEquals(outcome, restaurantDto);

    }

    @Test
    public void controllerShouldReturn400StatusWhenTheIdDoesNotExist() throws Exception {
        //when(restaurantService.getRestaurantDtoById("100")).thenReturn(Optional.of(restaurantDto));
    }

}
