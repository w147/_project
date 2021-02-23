package com.plaso.thread.pool;

@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
