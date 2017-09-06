package com.wangwenjun.concurrency.mycode;




/**
 * Created by john on 2017/8/29.
 */
public class OtherService {
    private Object  lock = new Object();
    private DeadLocak deadLock;

    public void setDeadLock(DeadLocak deadLock) {
        this.deadLock = deadLock;
    }


    public  void s1(){
        synchronized (lock){
            System.out.println("s1");
        }
    }
    public void s2(){
        synchronized (lock){
            System.out.println("s2");
            deadLock.m2();
        }
    }


}
