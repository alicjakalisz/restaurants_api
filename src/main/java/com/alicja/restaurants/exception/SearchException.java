package com.alicja.restaurants.exception;

public class SearchException extends RuntimeException{
    public SearchException(String errorMessage){
        super(errorMessage);
    }
}
