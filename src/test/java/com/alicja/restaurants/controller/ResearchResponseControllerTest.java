package com.alicja.restaurants.controller;


import com.alicja.restaurants.dto.ResearchResponseDto;
import com.alicja.restaurants.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.core.type.TypeReference;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;


@WebMvcTest(ResearchResponseController.class)
public class ResearchResponseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //we are mocking the service object as we need it for controller testing
    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void controllerShouldGetRestaurantsFromServiceBasedOnSearchParam() throws Exception {

        List<ResearchResponseDto> list = new ArrayList<>();
        list.add(new ResearchResponseDto("1","EatActiv","Poland Street","5",Optional.of(3),"link"));
        list.add(new ResearchResponseDto("2","Pho","UpperStreet","4",Optional.of(3),"link2"));

        //mocking service method and its result
        when(restaurantService.getResearchResults("London", Optional.of("Vietnamese"),Optional.of(500),Optional.of(5))).thenReturn(list);

        ResultActions resultActions = mockMvc.perform(get("/restaurants/search?location=London&cuisine=Vietnamese&radius=500&rating=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("EatActiv")))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].name", is("Pho")));

        MvcResult result = resultActions.andReturn();
        String contentString = result.getResponse().getContentAsString();
        System.out.println(contentString);
        //reading json string and mapping it into object
        List<ResearchResponseDto> outcome = objectMapper.readValue(contentString, new TypeReference<List<ResearchResponseDto>>() {
        });
        assertEquals(outcome, list);
    }
    @Test
    public void shouldReturnErrorResponseIfLocationParamNotEntered() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/restaurants/search?cuisine=Vietnamese&radius=500&rating=5"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void shouldReturnErrorResponseIfLocationParamNotEntered() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/restaurants/search?cuisine=Vietnamese&radius=500&rating=5"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
