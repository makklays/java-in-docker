package com.techmatrix18.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Example of Rabbit Listener
 *
 * @author alexander
 * @since 21-03-2025
 * @version 0.0.1
 */

@Service
public class RabbitEventListenerService {

    Logger log = Logger.getLogger(RabbitEventListenerService.class.getName());

    @RabbitListener(queues = "#{T(com.techmatrix18.config.RabbitMQConfig).QUEUE}")
    public void handleEvent(Object event) {
        log.info("Received event: " + event);
    }
}

