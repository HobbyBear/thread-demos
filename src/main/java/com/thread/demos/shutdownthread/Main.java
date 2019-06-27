package com.thread.demos.shutdownthread;

/**
 * @author: xch
 * @create: 2019-06-24 13:21
 **/
public class Main {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
                while (true){
                    if (Thread.interrupted()){
                        break;
                    }
                }
                // 执行之后的操作
        });
        t.start();
        t.interrupt();

        ThreadService threadService = new ThreadService();
        threadService.execute(() -> {
            while (true) {

            }
        });
        threadService.shutdown(1000 * 3);

    }

}
