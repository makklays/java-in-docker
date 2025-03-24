package com.techmatrix18.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaConsumer {

    Logger log = Logger.getLogger(KafkaConsumer.class.getName());

    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(String message) {
        log.info("Received message in group 'my-group': " + message);
    }
}

