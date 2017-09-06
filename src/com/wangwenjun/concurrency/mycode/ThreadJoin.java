package com.wangwenjun.concurrency.mycode;

import java.util.stream.IntStream;

/**
 * Created by john on 2017/8/30.
 *
 * t1执行完执行下一个，最后执行main的
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()));
        });
        t1.start();
        Thread t2 = new Thread(()->{
            IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName()));
        });
        t2.start();
        t1.join();
        t2.join();

        System.out.println("all have finished...");

    }
}
