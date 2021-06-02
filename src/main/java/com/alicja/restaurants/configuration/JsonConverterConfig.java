package com.alicja.restaurants.configuration;

import com.alicja.restaurants.json_converter.JsonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConverterConfig {
    @Bean
    public JsonConverter jsonConverter(){
        return new JsonConverter();
    }
}
