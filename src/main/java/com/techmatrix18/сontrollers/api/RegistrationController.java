package com.techmatrix18.сontrollers.api;

import com.techmatrix18.config.RabbitMQConfig;
import com.techmatrix18.model.MyUser;
import com.techmatrix18.repositories.UserRepository;
import com.techmatrix18.services.RabbitEventPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RegistrationController {

    Logger log = Logger.getLogger(RegistrationController.class.getName());

    private RabbitEventPublisherService rabbitPublisherService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder, RabbitEventPublisherService rabbitPublisherService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rabbitPublisherService = rabbitPublisherService;
    }

    @PostMapping(value = "/req/signup", consumes = "application/json")
    public MyUser createUser(@RequestBody MyUser user) {
        System.out.println(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // send model 'user' to rabbitMQ queue 'registration'
        rabbitPublisherService.sendMessage(RabbitMQConfig.QUEUE_REQ, user); // object user

        log.info("[x] Log message to rabbitMQ:" + user.toString());

        return userRepository.save(user);
    }
}

