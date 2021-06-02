package com.alicja.restaurants.mapper;

import com.alicja.restaurants.dto.RestaurantDto;
import com.alicja.restaurants.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestaurantMapper implements Mapper<RestaurantDto, Restaurant> {


    Logger logger = LoggerFactory.getLogger(RestaurantMapper.class);

    @Override
    public RestaurantDto fromEntityToDto(Restaurant entity) {
        RestaurantDto restaurantDto = new RestaurantDto(entity.getId(),
                entity.getAddress(), entity.getRating(), entity.getPrice_level(),
                entity.getPhoto(), entity.getWebsite(), entity.getUser_rating_total(), entity.getPhone_number(), entity.getComments());
        logger.info("Converting entity : [{}] into dto [{}]",entity,restaurantDto);
        return restaurantDto;
    }

    @Override
    public Restaurant fromDtoToEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant(restaurantDto.getId(), restaurantDto.getAddress(),
                restaurantDto.getRating(), restaurantDto.getPrice_level(), restaurantDto.getPhoto(),
                restaurantDto.getWebsite(),restaurantDto.getUser_rating_total(),restaurantDto.getPhone_number(),restaurantDto.getComments());
        logger.info("Converting restaurantDto [{}] into entity [{}]",restaurantDto,restaurant);
        return restaurant;
    }
}
