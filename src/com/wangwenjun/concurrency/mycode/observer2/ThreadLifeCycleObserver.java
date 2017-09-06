package com.wangwenjun.concurrency.mycode.observer2;

import java.util.List;

/**
 * Created by john on 2017/9/2.
 *
 * 这个类是线程观察者
 */
public class ThreadLifeCycleObserver implements  LifeCycleListener {

    private  final Object LOCK = new Object();

    //并发的去查询一个集合
    public void concurrentQuery(List<String> ids){
        if(ids ==null || ids.isEmpty()){
            return;
        }
        ids.stream().forEach(id->new Thread(new ObserverableRunnable(this) {
            @Override
            public void run() {
                System.out.println("启动线程了.");
                notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                System.out.println("query for id" + id);
                try {
                    Thread.currentThread().sleep(1000L);
                } catch (InterruptedException e) {
                    notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),e));
                }
                notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
            }
        }).start());
    }
    @Override
    public void setEvent(ObserverableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println(event.getThread().getName()+" has changed the state" +event.getThread().getState());
            if(event.getCause() != null){
                event.getCause().printStackTrace();
            }
        }
    }
}
