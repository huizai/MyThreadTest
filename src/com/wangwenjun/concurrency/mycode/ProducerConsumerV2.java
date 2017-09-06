package com.wangwenjun.concurrency.mycode;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by john on 2017/8/29.
 *
 * 此处有个坑，如果你用的是Notify（）这个有可能把所有的线程都wait
 * 此处还有一个坑，如果你用if多个线程生产者容易造成多生产者或者消费者的情况
 */
public class ProducerConsumerV2 {
    private int i = 0;
    private final Object lock = new Object();

    private volatile boolean  isProducer= false;

    public void produce(){
        synchronized (lock){
            while(isProducer){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            i++;
            System.out.println("P->"+i);
            lock.notifyAll();
            isProducer = true;

//            if(isProducer){
//
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }else{
//                i++;
//                System.out.println("P->"+i);
//                lock.notifyAll();
//                isProducer = true;
//
//
//            }
        }


    }


    public void consume(){
        synchronized (lock){
            while(!isProducer){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("C->"+i);
            lock.notifyAll();
            isProducer = false;
//            if(isProducer){
//                System.out.println("C->"+i);
//
//                lock.notifyAll();
//                isProducer = false;
//
//            }else{
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }


    public static void main(String[] args) {
        ProducerConsumerV2 pc = new ProducerConsumerV2();
        Stream.of("P1","P2","p3","p4").forEach(n->{
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        pc.produce();
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        });
        Stream.of("C1","C2","C3","C4").forEach(n->{

            new Thread(){
                @Override
                public void run() {
                    while(true){
                        pc.consume();
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }.start();
        });


    }



}