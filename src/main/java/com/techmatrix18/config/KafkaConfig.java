package com.techmatrix18.config;

import org.apache.kafka.clients.admin.NewTopic;
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

    // Create topic with 3 partition
    @Bean
    public NewTopic userProtoTopic() {
        return new NewTopic("topic.3-partition.protos.user", 3, (short) 1); // 3 партиции, replication factor = 1
    }
}

