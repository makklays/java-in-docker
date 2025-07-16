package com.techmatrix18.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDtoJsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialization() throws Exception {
        UserDto userDto = new UserDto("Alex", "my_password_hash");
        String json = objectMapper.writeValueAsString(userDto);

        assertTrue(json.contains("\"username\":\"Alex\""));
        assertTrue(json.contains("\"password\":\"my_password_hash\""));
    }

    @Test
    public void testDeserialization() throws Exception {
        String json = "{\"username\":\"Alex\", \"password\":\"my_password_hash\"}";
        UserDto user = objectMapper.readValue(json, UserDto.class);

        assertEquals("Alex", user.getUsername());
        assertEquals("my_password_hash", user.getPassword());
    }
}

