package com.techmatrix18.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techmatrix18.model.User;
import com.techmatrix18.services.BaseService;
import com.techmatrix18.services.MapService;
import com.techmatrix18.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 */

@RestController
@Tag(name = "Mobile App", description = "Mob App management API")
public class MobAppController {

    // for log
    Logger log = Logger.getLogger(MobAppController.class.getName());

    private UserService userService;
    private MapService mapService;
    private BaseService baseService;
    private PasswordEncoder passwordEncoder;

    /**
     * Controller constructor with user service dependency injection.
     *
     * @param userService
     */
    public MobAppController(UserService userService, PasswordEncoder passwordEncoder, MapService mapService, BaseService baseService) {
        this.userService = userService;
        this.mapService = mapService;
        this.baseService = baseService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authorization a user.
     *
     * @param request
     * @return User response with token of user
     */
    @Operation(
        summary = "Auth user",
        description = "Authrization user by login/password",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(
                        example = "{\"login\": \"alex\", \"password\": \"1234\"}"
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Успешная авторизация",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                        example = "{\n" +
                                "  \"id\": 1,\n" +
                                "  \"email\": \"alex@example.com\",\n" +
                                "  \"mob\": \"+380991112233\",\n" +
                                "  \"gender\": \"male\",\n" +
                                "  \"age\": 30,\n" +
                                "  \"avatar\": \"https://site.com/avatar.jpg\",\n" +
                                "  \"message\": \"You are successfully authorized!\"\n" +
                                "}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Ошибка авторизации",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                        example = "{ \"message\": \"User not found or password incorrect\" }"
                    )
                )
            )
        }
    )
    @PostMapping(value="/api/v1/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> authUser(@RequestBody Map<String, String> request) {
        String login = request.get("login");
        String password = request.get("password");

        User user = userService.getByUsername(login);
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();

        if (user != null) {
            // check hash password
            if (passwordEncoder.matches(password, user.getPassword())) {
                node.put("id", user.getId());
                //node.put("token", user.getToken()); // token (!)
                //node.put("score", user.getScore()); // score (!)
                node.put("email", user.getEmail());
                node.put("mob", user.getMob());
                node.put("gender", user.getGender());
                node.put("age", user.getAge());
                node.put("avatar", user.getAvatar());
                node.put("message", "You are successfully authorized!");

                return ResponseEntity.ok(node);
            }
        }

        node.put("message", "User not found or password incorrect");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(node);
    }

    /**
     * List of bases by Space ID.
     *
     * @param request
     * @param authHeader
     * @return
     */
    @Operation(
        summary = "List of bases",
        description = "Get list of bases by Bearer token",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(example = "{\"spaceId\": \"123\"}")
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Успешно",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                        value = "{\"spaceId\": 1, \"sectors\": [{...}]}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Неавторизован",
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                        value = "{\"message\": \"Недействительный токен\"}"
                    )
                )
            )
        }
    )
    @PostMapping(value="/api/v1/bases", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> getBases(@RequestBody Map<String, String> request, @RequestHeader("Authorization") String authHeader) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response = objectMapper.createObjectNode();

        // 1. Verificación y extracción del token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("message", "Missing or invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = authHeader.substring(7); // удаляем "Bearer "
        User user = userService.getByToken(token);

        if (user == null) {
            response.put("message", "Недействительный токен");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // 2. Verificación spaceId
        String spaceIdStr = request.get("spaceId");
        if (spaceIdStr == null) {
            response.put("message", "spaceId обязателен");
            return ResponseEntity.badRequest().body(response);
        }

        Long spaceId;
        try {
            spaceId = Long.parseLong(spaceIdStr);
        } catch (NumberFormatException e) {
            response.put("message", "Неверный формат spaceId");
            return ResponseEntity.badRequest().body(response);
        }

        // 3. Obtener sectores
        List<com.techmatrix18.model.Map> sectors = mapService.getAllSectors(spaceId);

        response.put("spaceId", spaceId);
        response.set("sectors", objectMapper.valueToTree(sectors));

        return ResponseEntity.ok(response);
    }
}

