package com.wangwenjun.concurrency.mycode.observer;

/**
 * Created by john on 2017/9/2.
 */
public class BinaryObserver extends Observer {


    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("binaryobserver"+this.subject.getState());
    }
}
