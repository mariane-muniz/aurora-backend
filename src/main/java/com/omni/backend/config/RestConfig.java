package com.omni.backend.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
        config.setReturnBodyForPutAndPost(true);
        config.getExposureConfiguration().disablePutForCreation();
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.VISIBILITY);
    }
}