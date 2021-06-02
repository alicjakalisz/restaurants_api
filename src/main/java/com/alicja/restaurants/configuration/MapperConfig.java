package com.alicja.restaurants.configuration;

import com.alicja.restaurants.mapper.ResearchResponseMapper;
import com.alicja.restaurants.mapper.RestaurantMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

@Bean
public RestaurantMapper restaurantMapper(){
    return new RestaurantMapper();
}

@Bean
public ResearchResponseMapper researchResponseMapper(){
    return new ResearchResponseMapper();
}

}
