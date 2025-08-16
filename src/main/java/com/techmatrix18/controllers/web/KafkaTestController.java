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
    private final KafkaTemplate<String, String> kafkaStringTemplate;

    public KafkaTestController(KafkaProducer kafkaProducer, @Qualifier("kafkaStringTemplate") KafkaTemplate kafkaStringTemplate, UserService userService) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaStringTemplate = kafkaStringTemplate;
        this.userService = userService;
    }

    @Operation(summary = "Send message (string)", description = "Send simple message via Kafka (topic)")
    @PostMapping("/kafka-send")
    public String sendMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka topic";
    }

    @Operation(summary = "Send message (string)", description = "Send simple message via Kafka (other topic)")
    @PostMapping("/kafka-send-order")
    public String sendOrder(@RequestParam("text") String text, @RequestParam("price") String price) {
        kafkaStringTemplate.send("topic.orders.new","text: " + text + ", price: " + price);
        return "You had added a new order successfully!";
    }

    @Operation(summary = "Send message (proto) by userID", description = "Send User-protobuf via Kafka (topic)")
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

    @Operation(
            summary = "Send message (proto) via partition 0 by user_id",
            description = "Send User-protobuf via Kafka (partition and topic)"
    )
    @PostMapping("/kafka-partition-send-proto")
    public String sendPartitionUserProto(@RequestParam("user_id") String user_id, @RequestParam("money") String money) {

        // get proto
        User entity = userService.getById(Long.valueOf(user_id));
        if (entity != null) {
            com.techmatrix18.protobuf.UserProto.User userProto = com.techmatrix18.protobuf.UserProto.User.newBuilder()
                    .setId(entity.getId().intValue())
                    .setName(entity.getUsername())
                    .setEmail(entity.getEmail())
                    .build();

            // send proto
            kafkaProducer.sendOnePartitionMessageProto("topic.protos.user", 0, user_id, userProto.toByteArray());
            kafkaProducer.sendOnePartitionMessageProto("topic.protos.user", 0, user_id, userProto.toByteArray());
            kafkaProducer.sendOnePartitionMessageProto("topic.protos.user", 0, user_id, userProto.toByteArray());
        }

        return "You had sent money " + money + " successfully!";
    }

    @Operation(
            summary = "Send message (User proto) to 3 partitions Kafka by user_id",
            description = "Sends a User protobuf message to a Kafka topic with 3 partitions. " +
                    "The partition is selected automatically based on user_id hash, " +
                    "so messages from the same user go to the same partition. " +
                    "Listener(s) can consume from all 3 partitions in parallel."
    )
    @PostMapping("/kafka-3-partition-send-proto")
    public String send3PartitionUserProto(@RequestParam("user_id") String user_id, @RequestParam("money") String money) {

        // get proto
        User entity = userService.getById(Long.valueOf(user_id));
        if (entity != null) {
            com.techmatrix18.protobuf.UserProto.User userProto = com.techmatrix18.protobuf.UserProto.User.newBuilder()
                    .setId(entity.getId().intValue())
                    .setName(entity.getUsername())
                    .setEmail(entity.getEmail())
                    .build();

            // send proto
            // every user_id to one of tree partition - and listen automatically from tree partitions
            kafkaProducer.sendSeveralPartitionMessageProto("topic.3-partition.protos.user", user_id, userProto.toByteArray());
        }

        return "You had sent money " + money + " successfully!";
    }
}

