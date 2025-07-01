package com.techmatrix18;

import java.util.concurrent.*;

/**
 * Examples of Callable (to my mom:))
 *
 * @author Alexander Kuziv makklays@gmail.com
 * @since 01-07-2025
 * @version 0.0.1
 */

public class MyCallables {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Creamos una tarea Callable
        Callable<String> task = () -> {
            Thread.sleep(2000); // Imitamos una operación larga
            return "El resultado de Callable";
        };

        /*Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000); // Simulamos una tarea larga
                return "Resultado desde Callable";
            }
        };*/

        // Enviamos la tarea para su ejecución y obtenemos un Future
        Future<String> future = executor.submit(task);

        // Aquí se pueden ejecutar otras acciones en paralelo...

        // Obtenemos el resultado (bloquea el hilo hasta que la tarea termine)
        String result = future.get();
        System.out.println("Resultado " + result);

        // Terminamos el trabajo ExecutorService
        executor.shutdown();
    }
}

