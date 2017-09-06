package com.wangwenjun.concurrency.mycode.observer2;

/**
 * Created by john on 2017/9/2.
 * 这个类包装了一下Runable,使他具有一个告诉监听器当前线程是个什么状态的功能
 *
 */
public abstract class ObserverableRunnable implements  Runnable {
    final protected  LifeCycleListener listener;

    public ObserverableRunnable(LifeCycleListener listener) {
        this.listener = listener;
    }

    protected  void notifyChange (final  RunnableEvent event){
        listener.setEvent(event);

    }


    public enum  RunnableState{
        RUNNING,ERROR,DONE;
    }

    /**
     * 这个内部类定义了当前线程的状态,
     * 正在运行,
     * 运行结束
     * 抛错误了
     */
    public static class RunnableEvent{
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }


    }
}
