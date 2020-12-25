package com.plaso.thread;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {

    public static String asd = "asd";

    public static void main(String[] args) {
        enjoyMusic();
        browseNews();
    }

    /**
     * Browse the latest news.
     */
    private static void browseNews() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (asd){

                    for (; ; ) {
                        System.out.println("Uh-huh, the good news.");
    //                    sleep(1);
                        try {
                            asd.notify();
                            asd.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    /**
     * Listening and enjoy the music.
     */
    private static void enjoyMusic() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (asd) {

                    for (; ; ) {
                        System.out.println("Uh-huh, the nice music.");
//                    sleep(1);
                        try {
                            asd.notify();
                            asd.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    private static void sleep(int seconds)    {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}