package com.plaso;

public class ThreadWait {

    public static void main(String[] args) {
        Object lock1 = new Object(), lock2 = new Object();
        Foo foo = new Foo();
        Thread a = new Thread(new Runnable(){
            @Override
            public void run() {
                foo.first();
                lock1.notify();
            }
        });
        Thread b = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foo.second();
                lock2.notify();
            }
        });
        Thread c = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    lock2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foo.third();
            }
        });
        a.start();
        b.start();
        c.start();
    }
}
