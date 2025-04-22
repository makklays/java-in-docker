package com.techmatrix18.controllers.web;

import com.techmatrix18.services.KafkaProducer;
import com.techmatrix18.services.RabbitEventPublisherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test of Rabbit Controller
 *
 * @author alexander
 * @since 24-03-2025
 * @version 0.0.1
 */

@RestController
public class RabbitTestController {

    private final RabbitEventPublisherService rabbitEventPublisherService;

    public RabbitTestController(RabbitEventPublisherService rabbitEventPublisherService) {
        this.rabbitEventPublisherService = rabbitEventPublisherService;
    }

    @PostMapping("/rabbitsend")
    public String sendMessage(@RequestParam("message") String message) {
        rabbitEventPublisherService.publishEvent(message);
        return "Message sent to RabbitMQ";
    }
}

