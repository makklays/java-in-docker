package com.techmatrix18.services;


import com.techmatrix18.model.Base;
import com.techmatrix18.model.User;
import com.techmatrix18.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service for managing users.
 * Provides methods for load user by username.
 *
 * @author alexander
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

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            var userObject = user.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObject.getUsername())
                    .password(userObject.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    /**
     * Finds all users
     *
     * @return found all users
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Finds a user by email.
     *
     * @param email User email
     * @return found user
     * @throws NoSuchElementException if the user is not found
     */
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.get().getId() != null) {
            return user.get();
        } else {
            throw(new NoSuchElementException("User with the email '" + email + "' not found"));
        }
    }

    /**
     * Finds a user by mob.
     *
     * @param mob User mobile
     * @return found user
     * @throws NoSuchElementException if the user is not found
     */
    public User findUserByMob(String mob) {
        Optional<User> user = userRepository.findByMob(mob);
        if (user.get().getId() != null) {
            return user.get();
        } else {
            throw(new NoSuchElementException("User with the mob '" + mob + "' not found"));
        }
    }

    /**
     * Add User
     *
     * @return boolean
     */
    public boolean addUser(User user) {
        User b = userRepository.save(user);
        if (!b.getUsername().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit User
     *
     * @return boolean
     */
    public boolean updateUser(User user) {
        User b = userRepository.save(user);
        if (!b.getUsername().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete User by UserID
     *
     * @return boolean
     */
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.get().getId() != null) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
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

