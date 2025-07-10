package com.techmatrix18.controllers;

import com.techmatrix18.controllers.web.UserViewController;
import com.techmatrix18.export.ExcelExportService;
import com.techmatrix18.export.PdfExportService;
import com.techmatrix18.services.ContactService;
import com.techmatrix18.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(UserViewController.class)
@WebMvcTest(controllers = UserViewController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class) // <-- отключаем spring security для авторизованной страницы
public class UserViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;
    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private ExcelExportService excelExportService;
    @MockBean
    private PdfExportService pdfExportService;

    @Test
    //@WithMockUser(username = "rio", roles = {"USER"}) // <-- для авторизованной страницы в spring security используем определенного пользователя
    public void testWelcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(content().string(
                        containsString("Welcome to...")
                ));
    }
}

