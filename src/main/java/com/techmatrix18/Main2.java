package com.techmatrix18;

/**
 * 4 Threads
 *
 * @author Alexander Kuziv makklays@gmail.com
 * @since 13-06-2025
 * @version 0.0.1
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {

        // 4 Threads
        Thread thread1 = new MyThread1();
        Thread thread2 = new MyThread2();
        Thread thread3 = new MyThread3();
        Thread thread4 = new MyThread4();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join(); // основной поток будет ждать, пока thread1 не завершится
        // java.util.concurrent

        // Monitoring
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Свободно памяти: " + runtime.freeMemory());
        System.out.println("Всего памяти: " + runtime.totalMemory());
        System.out.println("Макс. памяти: " + runtime.maxMemory());
    }
}

// And he created 4 threads
class MyThread1 extends Thread {
    public void run() {
        System.out.println("Поток - Pishon / פִּישׁוֹן");
        for(int i = 0; i <= 10; i += 2) {
            System.out.println("Чётное: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread2 extends Thread {
    public void run() {
        System.out.println("Поток - Gihon / גִּיחוֹן");
        for(int i = 1; i <= 10; i += 2) {
            System.out.println("Нечётное: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread3 extends Thread {
    public void run() {
        System.out.println("Поток - Hiddekel / חִדֶּקֶל");
    }
}

class MyThread4 extends Thread {
    public void run() {
        System.out.println("Поток - Perat / פְּרָת");
    }
}

