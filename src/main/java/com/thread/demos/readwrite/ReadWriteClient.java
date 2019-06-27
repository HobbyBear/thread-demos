package com.thread.demos.readwrite;

/**
 * @author: xch
 * @create: 2019-06-27 10:30
 **/
public class ReadWriteClient {

    public static void main(String[] args) {
        ReadWriteWorker worker = new ReadWriteWorker(new SharedData(5)) ;
        worker.read("1");
        worker.read("2");
        worker.write('a',"3");
        worker.write('c',"4");
        worker.read("0");
    }
}
