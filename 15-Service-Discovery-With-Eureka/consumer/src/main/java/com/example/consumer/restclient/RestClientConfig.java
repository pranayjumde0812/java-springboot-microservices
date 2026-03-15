package com.example.consumer.restclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    //    private static final String PROVIDER_URL = "http://localhost:8081";
    private static final String PROVIDER_URL = "http://provider";

    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient restClient(RestClient.Builder restClient) {
        return restClient.baseUrl(PROVIDER_URL)
                .build();
    }
}
