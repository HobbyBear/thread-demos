package com.thread.demos.listenthread;

import java.util.List;

/**
 * @author: xch
 * @create: 2019-06-26 15:37
 **/
public class LifeCycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        ids.forEach(id -> {
            new Thread(new ObservableRunnable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                        System.out.println("query for the id->" + id);
                        notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                    } catch (Exception e) {
                        notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                    }
                }
            }, id).start();
        });
    }


    public void onEvent(ObservableRunnable.RunnableEvent runnableEvent) {
        synchronized (LOCK) {
            System.out.println("The runnable is [" + runnableEvent.getThread().getName() + "],the state is " + runnableEvent.getState());
            if (runnableEvent.getCause() != null){
                System.out.println("The runnable is [" + runnableEvent.getThread().getName() + "] process failed !");
                runnableEvent.getCause().printStackTrace();
            }
        }
    }
}
