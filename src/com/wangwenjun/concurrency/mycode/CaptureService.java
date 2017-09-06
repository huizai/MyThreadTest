package com.wangwenjun.concurrency.mycode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 2017/8/30.
 */
public class CaptureService {

    private  static LinkedList<Control> CONTROL = new LinkedList<>();


    public static void main(String[] args) {
        List<Thread> worker = new ArrayList<Thread>();

        Arrays.asList("M1","M12","M3","M4","M5","M6").stream()
        .map(CaptureService::createCaptureThread).forEach(t->{
            t.start();

        });

        worker.stream().forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("all capture work finshed");
    }

    private static Thread createCaptureThread(String name){
        return new Thread(() ->{
            //check work thread count.
            System.out.println("the work is begin "+Thread.currentThread().getName());
            synchronized (CONTROL){
                while(CONTROL.size()>5){
                    try {
                        CONTROL.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CONTROL.addFirst(new Control());
                }
            }

            System.out.println("the work is working ");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CONTROL){
                System.out.println("i am end ");
                CONTROL.removeFirst();
            }
        },name);
    }


    private  static class Control{

    }
}
