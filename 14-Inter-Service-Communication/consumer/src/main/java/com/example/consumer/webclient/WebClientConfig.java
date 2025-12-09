package com.example.consumer.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String PROVIDER_URL = "http://localhost:8081";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(PROVIDER_URL)
                .build();
    }
}
