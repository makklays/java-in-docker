package com.techmatrix18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("myQueue", false);
    }
}
