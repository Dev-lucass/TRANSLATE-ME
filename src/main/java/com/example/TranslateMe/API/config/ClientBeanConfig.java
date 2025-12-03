package com.example.TranslateMe.API.config;

import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientBeanConfig {

    @Bean
    public Client client() {
        return Client.builder().apiKey("AIzaSyB6Or_QK4A1I7tuZZWPvlPB6VJQPp3eMRU").build();
    }

}
