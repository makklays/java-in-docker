package com.techmatrix18.config;

import com.techmatrix18.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
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
                //.securityMatcher("/ws", "/ws/**") // - conflict with /req/logout and /req/login
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/v3/api-docs",
                            "/swagger-resources/**",
                            "/webjars/**"
                    ).permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/req/logout").permitAll()
                    .requestMatchers("/mystatic/uploads/base-levels/**").permitAll()
                    .requestMatchers("/mystatic/base-levels/**").permitAll()
                    .requestMatchers("/mystatic/**").permitAll()
                    .requestMatchers("/images/**").permitAll()
                    .requestMatchers("/logo/**").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/*.css", "/*.js", "/*.ico", "/*.png", "/*.jpg", "/*.svg", "/*.webapp").permitAll()
                    .requestMatchers("/req/login", "/req/index", "/welcome", "/api/v1/auth").permitAll()
                    .requestMatchers("/req/signup").permitAll()
                    .requestMatchers("/uploads/**").permitAll()
                    .requestMatchers("/mystatic/uploads/**").permitAll()

                    .requestMatchers("/api/v1/**").permitAll() // ⬅ открыть доступ

                    /*.requestMatchers(new AntPathRequestMatcher("/mystatic/base-levels/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/mystatic/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/mystatic/uploads/base-levels/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/imgs/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/*.{css,js}")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/*.{ico,png,jpg,svg,webapp}")).permitAll()*/

                    // authenticated
                    .requestMatchers("/map2").authenticated()
                    .requestMatchers("/map").authenticated()
                    .requestMatchers("/map/**/bases/").authenticated()
                    .requestMatchers("/map/**/bases/**").authenticated()
                    .requestMatchers("/map/**/base/**").authenticated()

                    .requestMatchers("/map/**/ti-centro").authenticated()
                    .requestMatchers("/map/**/base-1").authenticated()
                    .requestMatchers("/map/**/banco").authenticated()
                    .requestMatchers("/map/**/electriciti").authenticated()
                    .requestMatchers("/map/**/hierro").authenticated()
                    .requestMatchers("/map/**/agua").authenticated()
                    .requestMatchers("/map/**/comida").authenticated()
                    .requestMatchers("/map/**/iron").authenticated()

                    .requestMatchers("/unit-training/**").authenticated()
                    .requestMatchers("/unit/**").authenticated()
                    .requestMatchers("/diario-de-bordo/**").authenticated()

                    .requestMatchers("/la-historia").permitAll()
                    .requestMatchers("/info").permitAll()
                    .requestMatchers("/users").authenticated()
                    .requestMatchers("/rating").permitAll()
                    .requestMatchers("/settings").authenticated()
                    .requestMatchers("/contact").permitAll()
                    .requestMatchers("/profile").authenticated()
                    .requestMatchers("/users/**").authenticated()

                    .requestMatchers("/ws", "/ws/**").permitAll()

                    .requestMatchers("/admin/", "/admin/**").authenticated()
                )
                /*.formLogin(form -> form // удаляем полностью .formLogin( так как он не разрешает валидацию Dto и перехватывает POST раньше (!)
                    .loginPage("/req/login") // Specify the login page
                    .loginProcessingUrl("/req/login")
                    .permitAll()
                    //.defaultSuccessUrl("/welcome", true) // После успешного входа перенаправляем на главную
                )*/
                .exceptionHandling(exception -> exception // так как удалили .formLogin(), то всех неавторизованных отправляем на тсраницу авторизации кастомную (!)
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/req/login"))
                )
                .logout(logout -> logout
                        .logoutUrl("/req/logout")
                        .logoutSuccessUrl("/req/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
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

