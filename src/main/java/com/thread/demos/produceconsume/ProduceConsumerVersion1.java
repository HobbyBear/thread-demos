package com.thread.demos.produceconsume;

/**
 * @author: xch
 * @create: 2019-06-25 14:18
 **/
public class ProduceConsumerVersion1 {

    private int i = 1;

    private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("p->" + i++);
            isProduced = true;
            LOCK.notifyAll();
        }
    }

    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }


    public static void main(String[] args) {
        ProduceConsumerVersion1 produceConsumerVersion1 = new ProduceConsumerVersion1();
        new Thread(() -> {
            while (true) {
                produceConsumerVersion1.produce();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                produceConsumerVersion1.produce();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                produceConsumerVersion1.consume();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                produceConsumerVersion1.consume();
            }
        }).start();

    }
}
