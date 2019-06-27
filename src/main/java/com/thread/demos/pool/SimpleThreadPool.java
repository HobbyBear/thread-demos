package com.thread.demos.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * todo 能够自动维护线程池得大小
 * @author: xch
 * @create: 2019-06-25 09:47
 **/

public class SimpleThreadPool {

    /**
     * 线程数量
     */
    private int size;
    /**
     * 线程默认数量
     */
    private final static int DEFAULT_SIZE = 10;

    /**
     * 任务队列
     */
    private final LinkedList<Runnable> TASK_QUEUES = new LinkedList<>();

    /**
     * 线程队列
     */
    private final List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    /**
     * @Description: 提交任务
     * @Author: xch
     * @Date: 2019/6/25 11:53
    */
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUES) {
            TASK_QUEUES.addLast(runnable);
        }
    }

    private void init() {
        IntStream.rangeClosed(0, this.size).forEach(i -> {
            WorkTask workTask = new WorkTask();
            workTask.start();
            THREAD_QUEUE.add(workTask);
        });
    }

    /**
     * @Description: 关闭线程池中线程
     * @Author: xch
     * @Date: 2019/6/25 11:52
    */
    public void shutdown(){
        if (!TASK_QUEUES.isEmpty()){
            for (WorkTask workTask : THREAD_QUEUE){
                if (workTask.getThreadState().equals(ThreadState.BLOCKED)){
                    workTask.interrupt();
                    workTask.setThreadState(ThreadState.DEAD);
                }
            }
        }
    }

    /**
     * 线程状态
     */
    private enum ThreadState {
        FREE, RUNNABLE, BLOCKED, DEAD
    }

    private class WorkTask extends Thread {

        private volatile ThreadState threadState = ThreadState.FREE;

        @Override
        public void run() {
            Runnable runnable;
            while (!ThreadState.DEAD.equals(threadState)) {
                synchronized (TASK_QUEUES) {
                    while (TASK_QUEUES.isEmpty()) {
                        setThreadState(ThreadState.BLOCKED);
                        try {
                            TASK_QUEUES.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    runnable = TASK_QUEUES.removeFirst();
                }
                setThreadState(ThreadState.RUNNABLE);
                runnable.run();
                setThreadState(ThreadState.FREE);
            }
        }

        public ThreadState getThreadState() {
            return threadState;
        }

        public void setThreadState(ThreadState threadState) {
            this.threadState = threadState;
        }
    }


    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        IntStream.rangeClosed(1, 40).forEach(i -> {
            simpleThreadPool.submit(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable " + i + " serviced by " + Thread.currentThread().getName());
            });
        });
    }
}
