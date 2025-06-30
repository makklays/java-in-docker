package com.techmatrix18;

/**
 * Examples of Runnable
 *
 * @author Alexander Kuziv makklays@gmail.com
 * @since 30-06-2025
 * @version 0.0.1
 */

public class MyRunnables {
    Runnable run1 = new Runnable1();
}

class Runnable1 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Inicia un hilo 1  - " + Thread.currentThread().getName());
    }
}

