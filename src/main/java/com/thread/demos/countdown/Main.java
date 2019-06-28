package com.thread.demos.countdown;

/**
 * @author: xch
 * @create: 2019-06-28 09:52
 **/
public class Main {
    public static void main(String[] args) {
        MyCountDownLatch latch = new MyCountDownLatch(2);
        new Thread(()->{
            System.out.println("Thread name:"+Thread.currentThread().getName()+" execute");
            latch.countDown();
        },"1").start();
        new Thread(()->{
            System.out.println("Thread name:"+Thread.currentThread().getName()+" execute");
            latch.countDown();
        },"2").start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("以上线程全部执行完毕");

    }
}
