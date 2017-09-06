package com.wangwenjun.concurrency.mycode.mylock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by john on 2017/8/31
 * 这是一个自定义显示锁，可以支持超时功能.
 *
 * 这个叫保护模式。。。表示要慢慢体会。
 */
 public class BooleanLock  implements MyLock {


    private  Thread currentThread;
    //true lock have get
    //false lock is free other can get it
    private boolean initValue;
    //统计用的
    private  Collection<Thread> booleanThreadCollecton = new ArrayList<>();

    public BooleanLock(boolean initValue) {
        this.initValue = initValue;
    }

    public BooleanLock() {

    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue){
            booleanThreadCollecton.add(Thread.currentThread());
            this.wait();
        }
        booleanThreadCollecton.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread =  Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException , TimeOutExcepiotn {
        if(mills <= 0){
            lock();
        }

        long hasTime = mills;
        long endTime = System.currentTimeMillis()+mills;
        while (initValue){
            if (hasTime <= 0){
                throw  new TimeOutExcepiotn(" time out");
            }
            booleanThreadCollecton.add(Thread.currentThread());
            this.wait(mills);
            hasTime = endTime - System.currentTimeMillis();
        }
        this.initValue = true;

    }

    @Override
    public synchronized void unlock() {
        if(this.currentThread == Thread.currentThread()){
            this.initValue = false;
        booleanThreadCollecton.remove(Thread.currentThread());
            System.out.println(Thread.currentThread()+" release lock");
            this.notifyAll();
        }

    }

    @Override
    public Collection<Thread> getBlockedThreads() {
        return Collections.unmodifiableCollection(booleanThreadCollecton);
    }

    @Override
    public int getBlockThreadSize() {
        return booleanThreadCollecton.size();
    }
}

class LockTest{
    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3","T4").forEach(name->{
            new Thread(()->{
                try {
                    booleanLock.lock();
//                    try {
//                        booleanLock.lock(10L);
//                    } catch (MyLock.TimeOutExcepiotn timeOutExcepiotn) {
//                        timeOutExcepiotn.printStackTrace();
//                    }
                    Optional.of(Thread.currentThread().getName()+" have the lock").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    booleanLock.unlock();
                }
            },name).start();
        });

//        booleanLock.unlock();


        new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println(booleanLock.getBlockThreadSize()+"  size..");
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }


    private static void work(){
        try {
            System.out.println(Thread.currentThread().getName()+"is working ...");
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
