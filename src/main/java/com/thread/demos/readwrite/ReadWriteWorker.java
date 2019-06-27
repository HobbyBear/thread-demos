package com.thread.demos.readwrite;

/**
 * @author: xch
 * @create: 2019-06-27 10:31
 **/
public class ReadWriteWorker {
    private SharedData sharedData;

    public ReadWriteWorker(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void read(String threadName) {
        new Thread(() -> {
            while (true){
                System.out.println("ThreadName: "+Thread.currentThread().getName()+" -> "+String.valueOf(sharedData.read()));
            }
        },threadName).start();
    }

    public void write(char c,String threadName) {
        new Thread(() -> {
            while (true){
                sharedData.write(c);
            }
        },threadName).start();
    }
}
