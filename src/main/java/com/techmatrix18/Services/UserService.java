package com.techmatrix18.Services;


import com.techmatrix18.Model.MyUser;
import com.techmatrix18.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
}

