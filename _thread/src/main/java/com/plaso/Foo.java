package com.plaso;

public class Foo {
    public Foo() {

    }
    public void first() {
        System.out.println("first");
    }

    public void second() {
        System.out.println("second");
    }

    public void third() {
        System.out.println("third");
    }

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
