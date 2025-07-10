package com.techmatrix18.controllers.web;

import com.techmatrix18.dto.UserDto;
import com.techmatrix18.export.ExcelExportService;
import com.techmatrix18.export.PdfExportService;
import com.techmatrix18.model.User;
import com.techmatrix18.model.Contact;
import com.techmatrix18.services.ContactService;
import com.techmatrix18.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    private final ExcelExportService excelExportService;
    private final PdfExportService pdfExportService;

    public UserViewController(ContactService contactService,
                              UserService userService,
                              AuthenticationManager authenticationManager,
                              ExcelExportService excelExportService,
                              PdfExportService pdfExportService) {

        this.contactService = contactService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.excelExportService = excelExportService;
        this.pdfExportService = pdfExportService;
    }

    @GetMapping("/welcome")
    public String welcome(Model model, HttpSession session) {
        // get session
        Long userId = (Long) session.getAttribute("userId");
        log.info("---------- user ID--------------> " + userId);

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

        log.info("------------- User /req/login-post ---------> ");

        try {
            //String username = request.getParameter("username"); // not email (!)
            //String password = request.getParameter("password");

            log.info("---username---> " + userDto.getUsername() + "---password--->" + userDto.getPassword());

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(auth);

            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            User user = userService.findUserByUsername(userDto.getUsername());
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("email", user.getEmail());

            log.info("----User-auth------->" + auth.getPrincipal().toString());

            return "redirect:/welcome";

        } catch (AuthenticationException e) {
            model.addAttribute("loginError", true);

            log.info("---- User-auth ------- Exception -----------");

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
            return "redirect:/req/login";
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
        } else {
            return "redirect:/req/login";
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
        } else {
            return "redirect:/req/login";
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
        } else {
            return "redirect:/req/login";
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
    public String getAdmin(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        return "admin/index";
    }

    @GetMapping("/admin/users/")
    public String getAdminUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        log.info("Users: " + users.toString());

        return "admin/users/index";
    }

    @GetMapping("/admin/users/export/excel")
    public ResponseEntity<byte[]> exportClientsExcel() throws IOException {
        List<User> users = userService.getAll();

        ByteArrayInputStream in = excelExportService.exportUsersToExcel(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(in.readAllBytes());
    }

    @GetMapping("/admin/users/export/pdf")
    public ResponseEntity<byte[]> exportUsersPdf() {
        List<User> users = userService.getAll();
        ByteArrayInputStream in = pdfExportService.exportUsersToPdf(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(in.readAllBytes());
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

