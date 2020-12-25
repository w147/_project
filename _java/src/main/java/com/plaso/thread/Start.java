package com.plaso.thread;

import java.util.concurrent.TimeUnit;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("asd");
            }
        });
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(t1.getState());
        t1.start();
        System.out.println();
    }
}
