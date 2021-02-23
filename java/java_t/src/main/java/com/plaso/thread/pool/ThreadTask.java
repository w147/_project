package com.plaso.thread.pool;

//ThreadTask只是InternalTask和Thread的一个组合
public class ThreadTask {
    public ThreadTask(Thread thread, InternalTask internalTask) {
        this.thread = thread;
        this.internalTask = internalTask;
    }

    Thread thread;
    InternalTask internalTask;
}
