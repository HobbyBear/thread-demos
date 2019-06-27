package com.thread.demos.readwrite;

/**
 * 读和写之间的互斥关系
 * 关系| 读   |  写
 * ————————————————
 * 读    y   |   N
 * ————————————————
 * 写    N   |   N
 *
 * @author: xch
 * @create: 2019-06-27 08:21
 **/
public class ReadWriteLock {
    /**
     * 等待写的线程数量
     */
    private int waitingWriteLock = 0;
    /**
     * 正在写的线程数量
     */
    private int writingLock = 0;
    /**
     * 等待读的线程数量
     */
    private int waitingReadLock = 0;
    /**
     * 正在读的线程数量
     */
    private int readingLock = 0;


    public synchronized void readLock() {
        this.waitingReadLock++;
        try {
            while (this.writingLock > 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.readingLock++;
        this.waitingReadLock--;
    }

    public synchronized void unReadLock() {
        this.readingLock--;
        this.notifyAll();
    }

    public synchronized void writeLock() {
        this.waitingWriteLock++;
        try {
            while (this.writingLock > 0 || this.readingLock > 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.writingLock++;
        this.waitingWriteLock--;
    }

    public synchronized void unWriteLock() {
        this.writingLock--;
        this.notifyAll();
    }
}
