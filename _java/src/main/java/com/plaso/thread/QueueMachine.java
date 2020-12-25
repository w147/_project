package com.plaso.thread;

import java.util.ArrayList;
import java.util.List;

public class QueueMachine {
    Integer number = 1;
    Object lock = new Object();
    static List<Thread> machines = new ArrayList<>();

    public QueueMachine(int n){
        for (int i = 0; i < n; i++){
            char a = (char)(65 + i);
            Thread t1 = getMachine(String.valueOf(a));
            machines.add(t1);
        }
    }

    public static void main(String[] args) {
        Integer num = 4;
        new QueueMachine(num);
        Integer random = (int)(Math.random() * num);
        if(random == 4) random = 0;
        machines.get(random).notify();
    }

    private Integer getNumber(){
        return number++;
    }

    private Thread getMachine(String name){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (true){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Integer number = getNumber();
                        String name = Thread.currentThread().getName();
                        System.out.println(name +"-"+number);
                    }
                }
            }
        });
        t.setName(name);
        t.start();
        return t;
    }
}

