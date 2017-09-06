package com.wangwenjun.concurrency.mycode.observer;

/**
 * Created by john on 2017/9/2.
 */
public class OsserverClient {
    public static void main(String[] args) {
        final Subject subject = new Subject();
        new BinaryObserver(subject);
        System.out.println("=====1=========");
        subject.setState(10);
        System.out.println("======2========");
        subject.setState(20);

    }
}
