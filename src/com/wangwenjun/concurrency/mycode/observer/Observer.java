package com.wangwenjun.concurrency.mycode.observer;

/**
 * Created by john on 2017/8/31.
 */
public  abstract class Observer
{
    public Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract  void update();

}
