package com.techmatrix18.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Example of Kafka Producer
 *
 * @author alexander
 * @since 21-03-2025
 * @version 0.0.1
 */

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaStringTemplate;
    private final KafkaTemplate<String, byte[]> kafkaBytesTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaStringTemplate, KafkaTemplate<String, byte[]> kafkaBytesTemplate) {
        this.kafkaStringTemplate = kafkaStringTemplate;
        this.kafkaBytesTemplate = kafkaBytesTemplate;
    }

    // send message (string)
    public void sendMessage(String topic, String message) {
        kafkaStringTemplate.send(topic, message);
        System.out.println("Sent: " + message);
    }

    // send message (string) to topic: test-topic
    public void sendMessage(String message) {
        kafkaStringTemplate.send("test-topic", message);
    }

    // send proto
    public void sendMessageProto(String topic, byte[] data) {
        kafkaBytesTemplate.send(topic, data);
        System.out.println("Sent proto");
    }
}

