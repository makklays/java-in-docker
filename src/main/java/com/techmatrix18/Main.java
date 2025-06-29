package com.techmatrix18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Main class of the application
 *
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        Logger log = Logger.getLogger(Main.class.getName());

        // static or instance
        Example obj1 = new Example();
        Example obj2 = new Example();

        obj1.variable = 20;
        obj1.statVariable = 22;

        log.info("== log variable =>" + obj2.variable);
        log.info("== log variable =>" + obj2.statVariable);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password11";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Хеш пароля: " + hashedPassword);

        // run application
        SpringApplication.run(Main.class, args);
    }
}

/**
 * Example of using annotation @Component =))) jajaja
 * and statics variables
 *
 * @author Alexander Kuziv
 * @since 22-04-2025
 * @version 0.0.1
 */

@Component
class Example {
    int variable = 10;
    static int statVariable = 11;
}



