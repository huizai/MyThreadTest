package com.wangwenjun.concurrency.mycode;

/**
 * Created by john on 2017/8/29.
 */
public class DeadLocak {
    private  OtherService otherService ;
    public DeadLocak(){

    }
    public  DeadLocak(OtherService otherService){
        this.otherService = otherService;
    }
    private  final Object lock = new Object();
    public  void m1(){
        synchronized (lock){
            System.out.println("m1");
            otherService.s1();
        }
    }

    public void m2(){
        synchronized (lock){
            System.out.println("m2");
        }
    }
}
