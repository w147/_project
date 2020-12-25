package com.plaso.thread;

/**
 * 进程唤醒
 */
public class Wait {

}

class Employer {
    //雇主的锁对象，使用该command.notify方法来要工人干活
    static String command = "command";

    public static void main(String[] sss) throws InterruptedException {

        Employee mployee1 = new Employee(command);
        Employee mployee2 = new Employee(command);
        Employee mployee3 = new Employee(command);
        Employee mployee4 = new Employee(command);
        Employee mployee5 = new Employee(command);

        Thread t1 = new Thread(mployee1);
        Thread t2 = new Thread(mployee2);
        Thread t3 = new Thread(mployee3);
        Thread t4 = new Thread(mployee4);
        Thread t5 = new Thread(mployee5);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t4.setName("t4");
        t5.setName("t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        Thread.sleep(1000);

        //注意需要得到对应锁对象的使用权限
        synchronized (command) {
            //此次调用了两次notify则会有两个工人工作， 如果调用notifyAll则所有的线程都唤醒，所有的工人都干活

            command.notify();
            command.notify();
        }

    }
}

class Employee implements Runnable {
    String command;

    Employee(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (command) {
            System.out.println("I am " + name);
            try {
                // 工人在等待雇主的干活命令。 调用雇主的锁对象，挂起当前线程。
                command.wait();

                System.out.println("Employer "+ name);

            } catch (InterruptedException e) {
                //  TODO  Auto-generated catch block
                e.printStackTrace();
            }

//            System.out.println("I am working");
        }
    }
}