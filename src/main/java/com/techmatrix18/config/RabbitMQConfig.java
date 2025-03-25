package com.techmatrix18.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "myExchange";
    public static final String QUEUE = "myQueue";
    public static final String QUEUE_REQ = "registration";
    public static final String ROUTING_KEY = "myRoutingKey";
    public static final String ROUTING_KEY_REQ = "myRoutingKeyReq";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Queue queue_req() {
        return new Queue(QUEUE_REQ);
    }

    @Bean
    public Binding binding_req(Queue queue_req, DirectExchange exchange) {
        return BindingBuilder.bind(queue_req).to(exchange).with(ROUTING_KEY_REQ);
    }
}
