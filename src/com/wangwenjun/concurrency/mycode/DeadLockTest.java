package com.wangwenjun.concurrency.mycode;

/**
 * Created by john on 2017/8/29.
 */
public class DeadLockTest {

    public static void main(String[] args) {
        OtherService otherService = new OtherService();

        DeadLocak deadLocak = new DeadLocak();

        otherService.setDeadLock(deadLocak);

        new Thread("t1"){
            @Override
            public void run() {
                while(true){
                    deadLocak.m1();
                }
            }
        }.start();
        new Thread("t2"){
            @Override
            public void run() {
                while(true){
                    otherService.s2();
                }
            }
        }.start();
    }
}
