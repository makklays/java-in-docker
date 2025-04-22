package com.techmatrix18.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

/**
 * Конфигурация RabbitMQ для настройки очередей, обменников и биндингов.
 * Определяет базовые компоненты для обмена сообщениями через RabbitMQ.
 *
 * @author alexander
 * @since 21.03.2025
 * @version 0.0.1
 */

@Configuration
public class RabbitMQConfig {

    /**
     * Имя обменника.
     */
    public static final String EXCHANGE = "myExchange";

    /**
     * Имя очереди для получения сообщений.
     */
    public static final String QUEUE = "myQueue";

    public static final String QUEUE_REQ = "registration";

    /**
     * Routing key, по которому сообщения направляются в очередь.
     */
    public static final String ROUTING_KEY = "myRoutingKey";

    public static final String ROUTING_KEY_REQ = "myRoutingKeyReq";

    @Bean
    public DirectExchange exchange()  {
        return new DirectExchange(EXCHANGE);
    }

    /**
     * Создаёт очередь для получения сообщений.
     *
     * @return объект очереди
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    /**
     * Связывает очередь с обменником по routing key.
     *
     * @param queue очередь
     * @param exchange обменник
     * @return биндинг между очередью и обменником
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange)  {
        return  BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
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

