package com.techmatrix18.services;

import com.techmatrix18.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Example of Rabbit Publisher
 *
 * @author alexander
 * @since 21-03-2025
 * @version 0.0.1
 */

@Service
public class RabbitEventPublisherService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitEventPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEvent(String event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
    }

    public void sendMessage(String queue, Object message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY_REQ, message);
    }
}

