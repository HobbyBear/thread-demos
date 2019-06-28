package com.thread.demos.future;

/**
 * @author: xch
 * @create: 2019-06-27 14:13
 **/
public class FutureClient {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        });
        System.out.println(future.get());
        System.out.println("哈哈哈哈");
    }
}
