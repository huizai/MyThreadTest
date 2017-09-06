package com.wangwenjun.concurrency.mycode;

/**
 * Created by john on 2017/8/29.
 *
 * 如果多个线程运行一个静态代码里的静态Synchronized是用的静态class锁
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        new Thread("T1"){
            @Override
            public void run() {
               SynchronizedStatic.m1();
            }
        }.start();
        new Thread("T2"){
            @Override
            public void run() {
               SynchronizedStatic.m2();
            }
        }.start();
        new Thread("T3"){
            @Override
            public void run() {
                SynchronizedStatic.m3();
            }
        }.start();

    }
}
