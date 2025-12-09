package com.example.consumer.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final String PROVIDER_URL = "http://localhost:8081";

    @Bean
    public RestClient restClient(RestClient.Builder restClient) {
        return restClient.baseUrl(PROVIDER_URL)
                .build();
    }
}
