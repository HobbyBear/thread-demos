package com.thread.demos.future;

/**
 * @author: xch
 * @create: 2019-06-27 13:47
 **/
public class FutureService {

    public <T> Future<T> execute(FutureTask<T> futureTask) {
        Future<T> future = new Future<>();
        new Thread(() -> {
            future.done(futureTask.call());
        }).start();
        return future;
    }
}
