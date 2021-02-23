package com.plaso;

public class ThreadJoin {

    public static void main(String[] args) {
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
        b.start();
        c.start();
    }
}
