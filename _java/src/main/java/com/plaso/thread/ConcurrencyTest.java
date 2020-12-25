package com.plaso.thread;

/**
 * 并行、串行 线程对比
 */
public class ConcurrencyTest {
    private static final long count = 100000001;

    public static void main(String[] args) throws InterruptedException{
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException{
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++){
                    a += 5;
                }
            }
        });
        thread.start();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int b = 0;
                for (long i = 0; i < count; i++){
                    b--;
                }
            }
        });
        thread1.start();

        thread.join();
        thread1.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency:"+time+"ms");
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++){
            a+=5;
        }
        int b = 0;
        for (long i = 0; i < count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:"+time+"ms");
    }
}
