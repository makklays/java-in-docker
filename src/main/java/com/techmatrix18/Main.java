package com.techmatrix18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Kuziv
 * @since 19-02-2025
 * @version 0.0.1
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        // static or instance
        Example obj1 = new Example();
        Example obj2 = new Example();

        obj1.variable = 20;
        obj1.statVariable = 22;

        System.out.println("== log variable =>" + obj2.variable);
        System.out.println("== log variable =>" + obj2.statVariable);

        //
        SpringApplication.run(Main.class, args);
    }
}

@Component
class Example {
    int variable = 10;
    static int statVariable = 11;
}

