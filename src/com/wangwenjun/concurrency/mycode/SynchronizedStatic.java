package com.wangwenjun.concurrency.mycode;

/**
 * Created by john on 2017/8/29.
 */
public class SynchronizedStatic {
    public synchronized  static void m1() {
        System.out.println("m1"+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized  static void  m2(){
        System.out.println("m2"+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public   static void  m3(){
        System.out.println("m3"+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
