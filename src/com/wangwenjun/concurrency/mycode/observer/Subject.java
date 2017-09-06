package com.wangwenjun.concurrency.mycode.observer;


import java.util.*;

/**
 * Created by john on 2017/8/31.
 *
 *
 * apache commmons 包源码里有设计模式。
 */
public class Subject {
    //
    private List<Observer> observers  = new ArrayList<>();

    private  int state;

    public int getState(){
        return this.state;
    }

    //附加到每一个observer
    public void attach(Observer observer){
        observers.add(observer);
    }

    public  void setState(int state){
        if(state == this.state){
            return;
        }
        //状态改变之后通知大家,我的状态改变了,请来膜拜吧
        this.state = state;
        notifyAllObserver();
    }

    public void notifyAllObserver(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
