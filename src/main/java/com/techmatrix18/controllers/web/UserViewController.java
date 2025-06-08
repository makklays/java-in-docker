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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import javax.management.remote.JMXAuthenticator;
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
    private final AuthenticationManager authenticationManager;

    public UserViewController(ContactService contactService, UserService userService, AuthenticationManager authenticationManager) {
        this.contactService = contactService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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
                        HttpServletRequest request,
                        HttpSession session) {
        //
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        try {
            String username = request.getParameter("username"); // not email (!)
            String password = request.getParameter("password");

            log.info("------username---> " + username + "-----password----->" + password);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username, password);

            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

            User user = userService.findUserByUsername(username);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("email", user.getEmail());

            log.info("----User-auth------->" + auth.getPrincipal().toString());

            return "redirect:/";

        } catch (AuthenticationException e) {
            model.addAttribute("loginError", true);

            return "auth/login"; // вернёт на форму с ошибкой
        }
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
        } else {
            return "redirect:/login";
        }

        return "index";
    }

    @GetMapping("/users")
    public String users(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }

        return "users/index";
    }

    @GetMapping("/rating")
    public String rating(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }

        return "rating";
    }

    @GetMapping("/settings")
    public String settings(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        String email = (String) session.getAttribute("email");
        model.addAttribute("email", email);

        return "settings";
    }

    @GetMapping("/contact")
    public String contact(Contact contact, Model model, HttpSession session) {
        model.addAttribute("contact", contact);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }

        return "contact";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        // get session
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }

        return "profile";
    }

    @PostMapping("/contact")
    public String contactPost(@Valid Contact contact, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {

            // get session
            Long userId = (Long) session.getAttribute("userId");
            model.addAttribute("userId", userId);
            if (userId != null) {
                User user = userService.getById(userId);
                model.addAttribute("user", user);
            }

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

