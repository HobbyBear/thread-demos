package com.thread.demos.future;

/**
 * @author: xch
 * @create: 2019-06-27 13:49
 **/
public class Future<T> {

    /**
     * 是否完成
     */
    private volatile boolean done;

    /**
     * 执行结果
     */
    private T result;

    /**
     * 供主线程调用
     * @return
     * @throws InterruptedException
     */
    public synchronized T get() throws InterruptedException {
        while (!done){
            this.wait();
        }
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /**
     * 供执行线程调用
     * @param result
     */
    public synchronized void done(T result){
        this.result = result;
        this.done = true;
        this.notifyAll();
    }
}
