package com.wangwenjun.concurrency.mycode.observer2;

/**
 * Created by john on 2017/9/2.
 * 这是一个线程生命周期的监听器,简单说就是线程在运行的时候比如有数据返回,有没有结束,有没有异常返回,都是通过这个监听器来通知调用者的.
 */
public interface LifeCycleListener {
    void setEvent(ObserverableRunnable.RunnableEvent event);
}
