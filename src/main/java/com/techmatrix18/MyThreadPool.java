package com.techmatrix18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Examples of Thread Pool
 *
 * @author Alexander Kuziv makklays@gmail.com
 * @since 30-06-2025
 * @version 0.0.1
 */

public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int taskNum = i;

            executor.execute(() -> {
                System.out.println("Ejuta la tarea " + taskNum + " del hilo " + executor.getClass().getName());
            });
        }

        executor.shutdown();
    }
}

