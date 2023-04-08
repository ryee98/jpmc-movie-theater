package com.jpmc.theater.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ryee on 4/7/23
 */
@Configuration
public class ApplicationConfig {

    /**
     * objectMapper - a configured Jackson ObjectMapper instance that is used for serializing the movie schedule.
     * @return An ObjectMapper instance configured with the JavaTimeModule and for pretty-printing the JSON output.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }
}
