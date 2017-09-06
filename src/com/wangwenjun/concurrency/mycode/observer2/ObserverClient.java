package com.wangwenjun.concurrency.mycode.observer2;

import java.util.Arrays;

/**
 * Created by john on 2017/9/2.
 */
public class ObserverClient {
    public static void main(String[] args) {
        System.out.println("#");
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("T1","T2"));
    }
}
