package com.techmatrix18.controllers.api;

import com.techmatrix18.config.RabbitMQConfig;
import com.techmatrix18.model.User;
import com.techmatrix18.repositories.UserRepository;
import com.techmatrix18.services.RabbitEventPublisherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 * Controller for registering users in the system.
 * Processes HTTP-requests, related to user registration.
 *
 * @author alexander
 * @since 19-02-2025
 * @version 0.0.1
 */

@RestController
public class RegistrationController {

    Logger log = Logger.getLogger(RegistrationController.class.getName());

    private RabbitEventPublisherService rabbitPublisherService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Controller constructor with user service dependency injection.
     *
     * @param userRepository
     * @param passwordEncoder
     * @param rabbitPublisherService
     */
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder, RabbitEventPublisherService rabbitPublisherService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rabbitPublisherService = rabbitPublisherService;
    }

    /**
     * Registering a new user.
     *
     * @param user MyUser with user data
     * @return MyUser response with registered user
     */
    @PostMapping(value = "/req/signup", consumes = "application/json")
    public User createUser(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user.toString());
        // with hash password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // send model 'user' to rabbitMQ queue 'registration'
        rabbitPublisherService.sendMessage(RabbitMQConfig.QUEUE_REQ, user); // object user

        HttpSession session = request.getSession();
        session.setAttribute("userId", 1L);

        log.info("[x] Log message to rabbitMQ:" + user.toString());

        return userRepository.save(user);
    }
}

