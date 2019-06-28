package com.thread.demos.obviouslylock;

import java.util.stream.Stream;

/**
 * @author: xch
 * @create: 2019-06-28 13:20
 **/
public class Main {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        Stream.of("1","2","3").forEach(t->{
            new Thread(()->{
                try {
                    myLock.lock(1L);
                    Thread.sleep(2000);
                    System.out.println("Thread name ->" +Thread.currentThread().getName()+" execute finished");
                    myLock.unLock();
                } catch (InterruptedException e) {
                    System.out.println("Thread name "+Thread.currentThread().getName()+ " "+ e.getMessage());
                }
            },t).start();
        });

    }
}
