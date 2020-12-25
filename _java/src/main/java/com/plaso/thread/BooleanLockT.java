package com.plaso.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLockT implements Lock {
    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) { //①
            while (locked) { //②
                blockedList.add(currentThread());
                this.wait();
            }
            blockedList.remove(currentThread());//③
            this.locked = true;//④
            this.currentThread = currentThread();//⑤
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) { //①
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0)  //②
                        throw new TimeoutException("can not get the lock during " + mills);
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    this.wait(remainingMills); //③
                    remainingMills = endMills - currentTimeMillis(); //④
                }
                blockedList.remove(currentThread());   //⑤
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock(){
        synchronized (this){
            if (currentThread == currentThread()){//①
                this.locked = false;    // ②
                this.notifyAll();        // ③
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return null;
    }

}