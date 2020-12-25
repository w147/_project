package com.plaso.thread;

import java.util.ArrayList;
import java.util.List;

public class TicketWindow2 implements Runnable{
    private final String name;
    private int index = 1;
    private List<String> numbers = new ArrayList<>();
    public TicketWindow2(String name){
        this.name = name;
    }
    @Override
    public void run() {
        long tID = Thread.currentThread().getId();
        while (index < 10){
            numbers.add(tID + "-"+(index++));
//            System.out.println(tID + "-"+(index++));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TicketWindow2 ticket = new TicketWindow2("A");
        Thread t1 = new Thread(ticket);
        Thread t2 = new Thread(ticket);
        Thread t3 = new Thread(ticket);
        Thread t4 = new Thread(ticket);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println(ticket.numbers);
    }
}