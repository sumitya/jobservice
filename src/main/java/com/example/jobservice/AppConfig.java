package com.example.jobservice;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // NOTE - here RestTemplateBuilder is used for zipkin tracing.
        return builder.build();
    }
}

