package com.plaso;

public class MainJoin {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Thread a,b,c;
        a = new Thread(new Runnable(){
            @Override
            public void run() {

                foo.first();
            }
        });
        b = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foo.second();
            }
        });
        c = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    b.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foo.third();
            }
        });
        a.start();
        a.join();
        b.start();
        b.join();
        c.start();
        c.join();
    }
}

