package com.wangwenjun.concurrency.mycode;

/**
 * Created by john on 2017/8/29.
 *
 * 这个是用来测试this 锁
 */
public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("T1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();


        new Thread("T2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class  ThisLock{
    private  final Object object = new Object();

    public   void  m1(){
        synchronized (object){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public  void m2(){
        synchronized (object){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
//    public synchronized  void  m1(){
//        System.out.println(Thread.currentThread().getName());
//        try {
//            Thread.sleep(10_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public synchronized void m2(){
//        System.out.println(Thread.currentThread().getName());
//        try {
//            Thread.currentThread().sleep(10_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}

