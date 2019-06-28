package com.thread.demos.obviouslylock;

/**
 * @author: xch
 * @create: 2019-06-28 11:43
 **/
public class MyLock {

    private volatile boolean locking = false;

    private volatile Thread lockThread;

    public synchronized void lock() throws InterruptedException {
        while (locking) {
            this.wait();
        }
        locking = true;
        lockThread = Thread.currentThread();
    }

    public synchronized void lock(long mili) throws InterruptedException {
        long endTime = System.currentTimeMillis() + mili;
        long hasRemaning = mili;
        while (locking) {
            if (hasRemaning <= 0) {
                System.out.println("Thread name" + Thread.currentThread().getName() + " timeout " + hasRemaning);
                throw new InterruptedException("超时了");
            }
            this.wait(hasRemaning);
            hasRemaning = endTime - System.currentTimeMillis();
        }
        locking = true;
        lockThread = Thread.currentThread();
    }

    public synchronized void unLock() {
        if (locking && Thread.currentThread().equals(lockThread)) {
            locking = false;
            this.notifyAll();
        }
    }

}
