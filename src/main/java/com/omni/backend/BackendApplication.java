package com.omni.backend;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@EnableJms
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan({"com.omni.aurora.core.model", "com.omni.backend.model"})
@EnableJpaRepositories({"com.omni.aurora.core.repository", "com.omni.backend.repository"})
@ComponentScan({"com.omni.aurora.core", "com.omni.backend", "com.omni.aurora.token"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("queue");
    }
}