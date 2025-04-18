package com.techmatrix18.controllers.web;

import com.techmatrix18.model.MyUser;
import com.techmatrix18.model.Contact;
import com.techmatrix18.services.ContactService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

/**
 * @author Alexander Kuziv
 * @since 18.02.2025
 * @version 0.0.1
 */

@Controller
//@RequestMapping("/")
public class UserController {

    private final ContactService contactService;

    public UserController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("vv", "V-V-V");
        return "welcome";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/req/login")
    public String login(MyUser myUser) {
        return "auth/login";
    }

    @GetMapping("/req/signup")
    public String reqistr() {
        return "auth/signup";
    }

    @GetMapping("/req/index")
    public String index(Model model) {
        model.addAttribute("vv", "V-V-V");
        return "index";
    }

    @GetMapping("/users")
    public String users() {
        return "users/index";
    }

    @GetMapping("/contact")
    public String contact(Contact contact, Model model) {
        model.addAttribute("contact", contact);

        return "contact";
    }

    @PostMapping("/contact")
    public String contactPost(@Valid Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }

        contactService.addContact(contact);

        System.out.println("Contact --> " + contact.toString());

        return "redirect:/contact";
    }

    @GetMapping("/map")
    public String getDiv(Model model) {
        model.addAttribute("title", "V-V-V");

        return "map";
    }

    @GetMapping("/builds")
    public String getBuilds(Model model) {
        model.addAttribute("title", "V-V-V");

        return "builds";
    }

    @GetMapping("/build/{id}")
    public String getBuild(@PathVariable Long id, Model model) {
        model.addAttribute("title", "V-V-V");
        model.addAttribute("id", id);

        return "build";
    }
}

