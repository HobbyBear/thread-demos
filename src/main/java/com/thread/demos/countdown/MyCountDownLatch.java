package com.thread.demos.countdown;

/**
 * @author: xch
 * @create: 2019-06-28 10:01
 **/
public class MyCountDownLatch {
    /**
     * 计数器
     */
    private volatile int counter;

    public MyCountDownLatch(int counter) {
        this.counter = counter;
    }


    public synchronized void countDown() {
        if (counter > 0) {
            counter--;
        }
        if (counter == 0) {
            this.notifyAll();
        }
    }

    public synchronized void await() throws InterruptedException {
        while (counter > 0) {
            this.wait();
        }
    }
}
