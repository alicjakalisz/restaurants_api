package com.alicja.restaurants.exception;

public class RestaurantIdException extends RuntimeException {

    public RestaurantIdException(String errorMessage) {
        super(errorMessage);
    }
}
