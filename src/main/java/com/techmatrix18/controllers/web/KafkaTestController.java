package com.techmatrix18.controllers.web;

import com.techmatrix18.services.KafkaConsumer;
import com.techmatrix18.services.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test of Kafka Controller
 *
 * @author alexander
 * @since 24-03-2025
 * @version 0.0.1
 */

@RestController
public class KafkaTestController {

    private final KafkaProducer kafkaProducer;

    public KafkaTestController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/kafkasend")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka topic";
    }
}

