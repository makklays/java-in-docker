package com.techmatrix18.Services;


import com.techmatrix18.Model.MyUser;
import com.techmatrix18.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

/**
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MyUser> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            var userObject = user.get();

            return User.builder()
                    .username(userObject.getUsername())
                    .password(userObject.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    // Example of global error
    /*public String validateUser(User user) {
        String message = "";
        if (user.getCountry() != null && user.getPhoneNumber() != null) {
            if (user.getCountry().equalsIgnoreCase("India")
                    && !user.getPhoneNumber().startsWith("91")) {
                message = "Phone number is invalid for " + user.getCountry();
            }
        }
        return message;
    }

    // Uses in controller
    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        String err = validationService.validateUser(user);
        if (!err.isEmpty()) {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }
        if (result.hasErrors()) {
            return "errors/addUser";
        }
        repository.save(user);
        model.addAttribute("users", repository.findAll());
        return "errors/home";
    }*/
}

