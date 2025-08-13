package com.techmatrix18.controllers.web;

import com.techmatrix18.services.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test of Kafka Controller
 *
 * @author Alexander Kuziv
 * @since 24-03-2025
 * @version 0.0.1
 */

@RestController
public class KafkaTestController {

    private final KafkaProducer kafkaProducer;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaTestController(KafkaProducer kafkaProducer, KafkaTemplate kafkaTemplate) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/kafkasend")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka topic";
    }

    @PostMapping("/kafka-order")
    public String sendOrder(@RequestParam("text") String text, @RequestParam("price") String price) {
        kafkaTemplate.send("topic.orders.new","text: " + text + ", price: " + price);
        return "You had added a new order successfully!";
    }
}

