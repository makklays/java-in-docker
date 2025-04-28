package com.techmatrix18.controllers;

import com.techmatrix18.controllers.web.UserViewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserViewController.class)
public class UserViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWelcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(content().string(
                        containsString("Welcome to...")
                ));
    }

}

