package com.plaso.model.observer;

/**
 * 观察者模式
 */
public interface Observable {
    // 事件、通知
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    // 获取当前任务的生命周期状态
    Cycle getCycle();

    void start();

    void interrupt();
}
