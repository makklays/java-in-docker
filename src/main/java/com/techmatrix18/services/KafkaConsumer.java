package com.techmatrix18.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
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
    public void listen(String message) throws Exception {
        log.info("====> Received message in group 'my-group': " + message);
    }

    @KafkaListener(topics = "topic.orders.new", groupId = "group-orders")
    public void listenOrders(String message) throws Exception {
        log.info("====> Kafka message: " + message);
    }

    @KafkaListener(
            topics = "topic.3-partition.protos.user",
            groupId = "user-proto-consumer",
            concurrency = "3" // 3 потока = 3 партиции
    )
    public void listenUserProto(ConsumerRecord<String, byte[]> record) {
        try {
            // deserialization protobuf
            com.techmatrix18.protobuf.UserProto.User user =
                    com.techmatrix18.protobuf.UserProto.User.parseFrom(record.value());

            log.info("====> Consumed from topic=" + record.topic() + " partition=" + record.partition() + " key=" + record.key() + " user=" + record.key() + " " + user );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

