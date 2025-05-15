package com.techmatrix18.config;

import com.techmatrix18.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Application security configuration.
 *
 * Configures access rules for HTTP endpoints, as well as session policy, CORS, CSRF, and
 * other security settings for a REST application.
 *
 * @author Alexander Kuziv
 * @since 18.02.2025
 * @version 0.0.1
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Configures the Spring Security security filter chain.
     *
     * @param http HTTP Security Configuration Object
     * @return chain of security filters (SecurityFilterChain)
     * @throws Exception in case of configuration errors
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                // We disable CSRF, because we work with REST API
                .csrf(csrf -> csrf.disable())

                // Configure CORS if necessary (you can additionally specify the CORS source)
                //.cors(Customizer.withDefaults())

                // Setting up access to endpoints
                .securityMatcher("/ws", "/ws/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/info").permitAll()
                    .requestMatchers("/mystatic/uploads/base-levels/**").permitAll()

                    .requestMatchers(new AntPathRequestMatcher("/mystatic/base-levels/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/mystatic/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/mystatic/uploads/base-levels/**")).permitAll()

                    .requestMatchers(new AntPathRequestMatcher("/imgs/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/*.{css,js}")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/*.{ico,png,jpg,svg,webapp}")).permitAll()
                    .requestMatchers("/req/login", "/req/index", "/welcome", "/api/v1/auth").permitAll()
                    .requestMatchers("/req/signup").permitAll()
                    .requestMatchers("/ws/**").permitAll()
                    .requestMatchers("/div/**", "build/**").permitAll()

                    .requestMatchers("/uploads/**").permitAll()
                    .requestMatchers("/mystatic/uploads/**").permitAll()
                    .requestMatchers("/mystatic/uploads/base-levels/**").permitAll()
                    // authenticated
                    .requestMatchers("/contact").authenticated()
                    .requestMatchers("/users/**").authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/req/login") // Specify the login page
                    .permitAll()
                    .defaultSuccessUrl("/welcome", true) // После успешного входа перенаправляем на главную
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

