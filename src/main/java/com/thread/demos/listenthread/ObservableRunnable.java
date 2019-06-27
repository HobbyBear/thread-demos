package com.thread.demos.listenthread;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: xch
 * @create: 2019-06-26 15:36
 **/
public abstract class ObservableRunnable implements Runnable {

    protected LifeCycleListener lifeCycleListener = null;

    public ObservableRunnable(LifeCycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    protected void notifyChange(RunnableEvent runnableEvent){
        this.lifeCycleListener.onEvent(runnableEvent);
    }

    public enum RunnableState {
        ERROR, RUNNING, DONE;
    }

    @Data
    @AllArgsConstructor
    public static class RunnableEvent {
        private RunnableState state;
        private Thread thread;
        private Throwable cause;
    }
}
