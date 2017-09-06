package com.wangwenjun.concurrency.mycode.mylock;

import java.util.Collection;

/**
 * Created by john on 2017/8/31.
 */
public interface MyLock {
    class TimeOutExcepiotn extends  Exception{
        public TimeOutExcepiotn(String message) {
            super(message);
        }
    }
    void lock() throws InterruptedException;

    void lock(long mills) throws  InterruptedException ,TimeOutExcepiotn;
    void unlock();

    Collection<Thread> getBlockedThreads();

    int getBlockThreadSize();
}
