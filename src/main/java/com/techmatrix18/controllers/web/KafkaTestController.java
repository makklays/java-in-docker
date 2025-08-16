package com.techmatrix18.controllers.web;

import com.techmatrix18.model.User;
import com.techmatrix18.services.KafkaProducer;
import com.techmatrix18.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaTestController(KafkaProducer kafkaProducer, @Qualifier("kafkaStringTemplate") KafkaTemplate kafkaTemplate, UserService userService) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
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

    @Operation(summary = "Send user by ID", description = "Send User-protobuf via Kafka to topic")
    @PostMapping("/kafka-send-proto")
    public String sendUserProto(@RequestParam("user_id") String user_id, @RequestParam("money") String money) {

        // get proto
        User entity = userService.getById(Long.valueOf(user_id));
        if (entity != null) {
            com.techmatrix18.protobuf.UserProto.User userProto = com.techmatrix18.protobuf.UserProto.User.newBuilder()
                    .setId(entity.getId().intValue())
                    .setName(entity.getUsername())
                    .setEmail(entity.getEmail())
                    .build();

            // send proto
            kafkaProducer.sendMessageProto("topic.protos.user", userProto.toByteArray());
            kafkaProducer.sendMessage("topic.user.money", money);
        }

        return "You had sent money " + money + " successfully!";
    }
}

