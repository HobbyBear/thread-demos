package com.thread.demos.future;

/**
 * @author: xch
 * @create: 2019-06-27 13:52
 **/
@FunctionalInterface
public interface FutureTask<T> {

    T call();
}
