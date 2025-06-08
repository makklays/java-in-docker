package com.techmatrix18.controllers.web;

import com.techmatrix18.dto.BaseLevelDto;
import com.techmatrix18.dto.UserDto;
import com.techmatrix18.model.Map;
import com.techmatrix18.model.User;
import com.techmatrix18.model.Contact;
import com.techmatrix18.services.ContactService;
import com.techmatrix18.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String info(HttpSession session, Model model) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }

        return "info";
    }

    @GetMapping("/req/login")
    public String login(Model model) {
        model.addAttribute("userDto", new UserDto());

        return "auth/login";
    }

    @PostMapping("/req/login")
    public String login(@Valid @ModelAttribute("userDto") UserDto userDto,
                        BindingResult bindingResult,
                        Model model,
                        HttpServletRequest request) {
        //
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        // TODO: find User by login and password

        HttpSession session = request.getSession();
        session.setAttribute("userId", 1L);

        return "redirect:/";
    }

    @GetMapping("/req/signup")
    public String reqistr() { return "auth/signup"; }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } /*else {
            return "redirect:/login";
        }*/

        return "index";
    }

    @GetMapping("/users")
    public String users() {
        return "users/index";
    }

    @GetMapping("/rating")
    public String rating() { return "rating"; }

    @GetMapping("/settings")
    public String settings() { return "settings"; }

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

