package com.techmatrix18.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Example of Kafka Consumer
 *
 * @author alexander
 * @since 21-03-2025
 * @version 0.0.1
 */

@Service
public class KafkaConsumer {

    // log
    Logger log = Logger.getLogger(KafkaConsumer.class.getName());

    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(String message) {
        log.info("Received message in group 'my-group': " + message);
    }
}

