package com.plaso.thread;

import java.util.ArrayList;
import java.util.List;

public class TicketWindow extends Thread{
    private final String name;
    private static int index = 1;
    private Object lock = 1;
    private static List<String> numbers = new ArrayList<>();
    public TicketWindow(String name){
        this.name = name;
    }
    @Override
    public void run() {
        while (index < 50){
            synchronized (lock){
                numbers.add(name+ "-"+(index++));
            }
//            System.out.println(name+ "-"+(index++));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TicketWindow t1 = new TicketWindow("A");
        TicketWindow t2 = new TicketWindow("B");
        TicketWindow t3 = new TicketWindow("C");
        TicketWindow t4 = new TicketWindow("D");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println(numbers);
    }
}