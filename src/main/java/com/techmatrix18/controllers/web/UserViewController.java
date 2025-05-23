package com.techmatrix18.controllers.web;

import com.techmatrix18.model.Map;
import com.techmatrix18.model.User;
import com.techmatrix18.model.Contact;
import com.techmatrix18.services.ContactService;
import com.techmatrix18.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import java.util.List;
import java.util.logging.Logger;

/**
 * User controller with endpoints for user pages
 *
 * @author Alexander Kuziv
 * @since 18.02.2025
 * @version 0.0.1
 */

@Controller
public class UserViewController {

    Logger log = Logger.getLogger(UserViewController.class.getName());

    private final ContactService contactService;
    private final UserService userService;

    public UserViewController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
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
    public String login(User myUser) {
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
        log.info("Contact --> " + contact.toString());

        return "redirect:/contact";
    }

    @GetMapping("/admin/")
    public String getAdmin() {
        return "admin/index";
    }

    @GetMapping("/admin/users/")
    public String getAdminUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        log.info("Users: " + users.toString());

        return "admin/users/index";
    }

    @GetMapping("/admin/home/")
    public String getAdminHome() {
        return "admin/home";
    }

    @GetMapping("/admin/settings/")
    public String getAdminSettings() {
        return "admin/settings";
    }
}

