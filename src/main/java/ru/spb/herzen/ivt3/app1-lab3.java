package ru.spb.herzen.ivt3;

import java.util.Random;

public class FirstThread implements Runnable {
    @Override
    public void run() {
        Random random = new Random();

        int startNumber = Math.abs(random.nextInt()) % 100;

        for (int i = startNumber; i < startNumber + 30; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
