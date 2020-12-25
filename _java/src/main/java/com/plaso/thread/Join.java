package com.plaso.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import static java.util.stream.Collectors.toList;

public class Join {
    // ①合作的各大航空公司
    private static List<String> flightCompany = Arrays.asList("CSA","CEA","HNA");
    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {
        final List<String> result = new ArrayList<>();
        // ②创建查询航班信息的线程列表
        List<FightQueryTask> tasks = flightCompany.stream().map(f -> createSearchTask(f, original, dest)).collect(toList());
        // ③分别启动这几个线程
        tasks.forEach(Thread::start);
        // ④分别调用每一个线程的join方法，阻塞当前线程
        tasks.forEach(t -> {
            try {
                t.join();
            }catch (InterruptedException e){

            }
        });
        //⑤在此之前，当前线程会阻塞住，获取每一个查询线程的结果，并且加入到result中
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest){
        return new FightQueryTask(fight, original, dest);
    }
}

interface FightQuery {
    List<String> get();
}

class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destination){
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight:%s list query successful\n", getName());
        } catch (InterruptedException e) {

        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}