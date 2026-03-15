package com.example.consumer.httpinterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class HttpInterfaceConfig {

//    private static final String PROVIDER_URL = "http://localhost:8081";

    private static final String PROVIDER_URL = "http://provider";

//    @Bean
//    public ProviderHttpInterface webClientHttpInterface() {
//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8081")
//                .build();
//
//        WebClientAdapter adapter = WebClientAdapter.create(webClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter)
//                .build();
//
//        ProviderHttpInterface service = factory.createClient(ProviderHttpInterface.class);
//
//        return service;
//    }

//    @Bean
//    public ProviderHttpInterface restClientHttpInterface() {
//        RestClient restClient = RestClient.builder()
//                .baseUrl("http://localhost:8081")
//                .build();
//
//        RestClientAdapter adapter = RestClientAdapter.create(restClient);
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter)
//                .build();
//
//        ProviderHttpInterface service = factory.createClient(ProviderHttpInterface.class);
//
//        return service;
//    }

    // Why this is commented cause
    // Rest template Bean is already present in app so thats why we used that
    // else we need to uncomment this

    //    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
    @Bean
    public ProviderHttpInterface restTemplateHttpInterface(RestTemplate restTemplate) {

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PROVIDER_URL));

        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        ProviderHttpInterface service = factory.createClient(ProviderHttpInterface.class);

        return service;
    }
}
