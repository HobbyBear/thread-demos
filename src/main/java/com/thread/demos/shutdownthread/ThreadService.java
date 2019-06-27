package com.thread.demos.shutdownthread;

/**
 * @author: xch
 * @create: 2019-06-24 13:36
 **/
public class ThreadService {

    private Thread executeThread;

    private boolean finished;

    public void execute(Runnable task){
        executeThread = new Thread(()->{
           Thread runner = new Thread(task);
           runner.setDaemon(true);
           runner.start();
            try {
                runner.join();
                finished = true;
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        });
        executeThread.start();
    }

    public void shutdown(long mills){
        long currentMills = System.currentTimeMillis();
        while (!finished){
            if (System.currentTimeMillis() - currentMills >= mills ){
                System.out.println("任务超时，需要结束");
                executeThread.interrupt();
                break;
            }
        }
    }
}
