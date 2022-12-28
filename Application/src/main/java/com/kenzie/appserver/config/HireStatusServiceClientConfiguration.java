package com.kenzie.appserver.config;

import com.kenzie.capstone.service.client.HireStatusServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** This file was created by another team member **/

@Configuration
public class HireStatusServiceClientConfiguration {

    @Bean
    public HireStatusServiceClient hireStatusServiceClient() {
        return new HireStatusServiceClient();
    }
}
