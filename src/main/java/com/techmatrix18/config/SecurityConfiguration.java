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
 * @author Alexander Kuziv
 * @since 18-02-2025
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable())
                //.cors(Customizer.withDefaults())
                //.authorizeHttpRequests(auth -> auth
                //    .requestMatchers("/req/login", "/css/**", "/js/**").permitAll() // Разрешаем доступ к логину и статике
                //    .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                //)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/info").permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/uploads/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/imgs/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/*.{css,js}")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/*.{ico,png,jpg,svg,webapp}")).permitAll()
                    .requestMatchers("/req/login", "/req/index", "/welcome", "/api/v1/auth").permitAll()
                    .requestMatchers("/req/signup").permitAll()
                    // authenticated
                    .requestMatchers("/contact").authenticated()
                    .requestMatchers("/users/**").authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/req/login") // Указываем страницу логина
                    .permitAll()
                    .defaultSuccessUrl("/welcome", true) // После успешного входа перенаправляем на главную
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                )
                //.httpBasic(Customizer.withDefaults()) - окно авторизации
                .build();

        /*return http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();*/

        /*return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpForm -> {
                    httpForm.loginPage("/req/login").permitAll();
                    httpForm.defaultSuccessUrl("index");
                })
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/req/signup", "/css/**", "/js/**");
                    registry.anyRequest().authenticated();
                })
                .build();*/
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

