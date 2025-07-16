package com.techmatrix18.controllers.api;

import com.techmatrix18.config.RabbitMQConfig;
import com.techmatrix18.model.User;
import com.techmatrix18.repositories.UserRepository;
import com.techmatrix18.services.RabbitEventPublisherService;
import com.techmatrix18.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.techmatrix18.protobuf.UserProto;
import java.io.IOException;
import java.util.List;
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
@Tag(name = "Users", description = "User management API")
public class UserController {

    Logger log = Logger.getLogger(UserController.class.getName());

    private RabbitEventPublisherService rabbitPublisherService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    /**
     * Controller constructor with user service dependency injection.
     *
     * @param userService
     * @param passwordEncoder
     * @param rabbitPublisherService
     */
    public UserController(UserService userService, PasswordEncoder passwordEncoder, RabbitEventPublisherService rabbitPublisherService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.rabbitPublisherService = rabbitPublisherService;
    }

    /**
     * Registering a new user.
     *
     * @param user MyUser with user data
     * @return MyUser response with registered user
     */
    @Operation(summary = "Create user", description = "Create User by parameters")
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

        userService.addUser(user);

        return user;
    }

    /**
     * Registering a new user from mobile application by URL from REST API
     *
     * @param request
     * @return
     */
    @Operation(summary = "Add User", description="Create User from JSON")
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
            userService.addUser(user);

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
    @Operation(summary = "Get one user", description = "Get one user by protocol protobuf")
    @GetMapping(value = "/api/v1/users/protobuf-grpc-google", produces = "application/x-protobuf")
    public byte[] getUserProto() {
        UserProto.User user = UserProto.User.newBuilder()
                .setId(1)
                .setName("Alex")
                .setEmail("alex@techmatrix18.com")
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
    @Operation(summary = "Get list of users", description = "Get list of users by protocol protobuf")
    @PostMapping(value = "/api/v1/users/protobuf-grpc-google", consumes = "application/x-protobuf")
    public ResponseEntity<String> receiveUser(@RequestBody byte[] data) throws IOException {
        UserProto.User user = UserProto.User.parseFrom(data);
        System.out.println("User: " + user.getName());
        return ResponseEntity.ok("Received");
    }

    /**
     * Get user by UserID
     *
     * @param userId
     * @return
     */
    @Operation(summary = "Get user by ID", description = "Get user by ID by protocol protobuf")
    @GetMapping(value = "/api/v1/users/protobuf-grpc-google-user-id/{userId}", produces = "application/x-protobuf")
    public ResponseEntity<UserProto.User> getUserById(@PathVariable Integer userId) {
        User entity = userService.getById(userId.longValue());
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        UserProto.User userProto = UserProto.User.newBuilder()
                .setId(entity.getId().intValue())
                .setName(entity.getUsername())
                .setEmail(entity.getEmail())
                .build();
        return ResponseEntity.ok(userProto);
    }

    /**
     * Get users by query
     *
     * @param query
     * @return
     */
    @Operation(summary = "Get user by query", description = "Get user by query by protocol protobuf")
    @GetMapping(value = "/api/v1/users/protobuf-grpc-google-query/{query}", produces = "application/x-protobuf")
    public ResponseEntity<UserProto.GetUsersResponse> getUsersByQuery(@PathVariable String query) {
        List<User> users = userService.getUsersByPartUsername(query);
        if (users == null || users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserProto.GetUsersResponse.Builder responseBuilder = UserProto.GetUsersResponse.newBuilder();
        for (User user : users) {
            UserProto.User userProto = UserProto.User.newBuilder()
                    .setId(user.getId().intValue())
                    .setName(user.getUsername())
                    .setEmail(user.getEmail())
                    .build();
            responseBuilder.addUsers(userProto);
        }

        return ResponseEntity.ok(responseBuilder.build());
    }
}

