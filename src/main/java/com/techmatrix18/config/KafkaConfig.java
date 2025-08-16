package com.techmatrix18.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    // KafkaTemplate для строк
    @Bean
    @Qualifier("kafkaStringTemplate")
    public KafkaTemplate<String, String> kafkaStringTemplate(ProducerFactory<String, String> factory) {
        return new KafkaTemplate<>(factory);
    }

    // KafkaTemplate для byte[]
    @Bean
    @Qualifier("kafkaBytesTemplate")
    public KafkaTemplate<String, byte[]> kafkaBytesTemplate(ProducerFactory<String, byte[]> factory) {
        return new KafkaTemplate<>(factory);
    }
}

