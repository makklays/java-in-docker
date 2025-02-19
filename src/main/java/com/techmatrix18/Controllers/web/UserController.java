package com.techmatrix18.Controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

/**
 * @author Alexander Kuziv
 * @since 18-02-2025
 * @version 0.0.1
 */

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("vv", "V-V-V");
        return "index";
    }

    @GetMapping("/req/login")
    public String login() {
        return "login";
    }

    @GetMapping("/req/signup")
    public String reqistr() {
        return "signup";
    }
}

