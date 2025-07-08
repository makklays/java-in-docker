package com.techmatrix18.controllers.api;

import com.techmatrix18.config.RabbitMQConfig;
import com.techmatrix18.model.User;
import com.techmatrix18.repositories.UserRepository;
import com.techmatrix18.services.RabbitEventPublisherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.techmatrix18.protobuf.UserProto;
import java.io.IOException;
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

    /**
     * Registering a new user from mobile application by URL from REST API
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/api/v1/mob-app-registr", consumes = "application/json")
    public String registrUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = passwordEncoder.encode(request.getParameter("password"));
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");

        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !mobile.isEmpty() && !gender.isEmpty() && !age.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setMob(mobile);
            user.setGender(gender);
            user.setAge(age);
            user.setPassword(password);
            userRepository.save(user);

            return "Успешно зарегистрировался!";

        } else {
            return "Не заполнено обязательное поле";
        }
    }

    /**
     * Get user on proto
     *
     * @return
     */
    @GetMapping(value = "/api/v1/users/proto", produces = "application/x-protobuf")
    public byte[] getUserProto() {
        UserProto.User user = UserProto.User.newBuilder()
                .setId(1)
                .setName("Alex")
                .setEmail("alex@example.com")
                .build();

        return user.toByteArray(); // бинарный ответ
    }

    /**
     * Add user from other system on proto
     *
     * @param data
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/api/v1/users/proto", consumes = "application/x-protobuf")
    public ResponseEntity<String> receiveUser(@RequestBody byte[] data) throws IOException {
        UserProto.User user = UserProto.User.parseFrom(data);
        System.out.println("User: " + user.getName());
        return ResponseEntity.ok("Received");
    }
}

