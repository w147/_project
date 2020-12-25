package com.plaso.thread;

/**
 * 一个线程加1操作n次，一个线程减1操作n次，查看结果
 */
public class ConcurrencyTest1 {
    private static final long count = 100000;
    private static long num = 0;

    public static void main(String[] args) throws InterruptedException{
        Thread add = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++){
//                    long tmp = num;
                    num = num + 1;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("+1: "+ num);
                }
            }
        });
        Thread sub = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++){
//                    long tmp = num;
                    num = num - 1;
//                    System.out.println("-1: "+ num);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        add.start();
        sub.start();
        System.out.println("main: "+num);
        add.join();
        sub.join();
        System.out.println("main: "+num);
    }
}
