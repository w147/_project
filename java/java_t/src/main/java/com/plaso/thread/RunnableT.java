package com.plaso.thread;

public class RunnableT {
    public static void main(String[] args) {
        A a = new A();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);
        t1.start();
        t2.start();
    }
}

class A implements Runnable{
    int i = 1;
    @Override
    public void run() {
        System.out.println(i++);
//        long threadId = Thread.currentThread().getId();
//        System.out.println(threadId);
    }
}