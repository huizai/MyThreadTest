package com.wangwenjun.concurrency.mycode;

/**
 * Created by john on 2017/8/29.
 */
public class ProduceConsumerV1
{
    private  int i =1;
    final private  Object lock = new Object();
    private  void producer(){
        synchronized (lock){
            System.out.println("P->"+i++);
        }
    }
    private  void consumer(){
        synchronized (lock){
            System.out.println("c->"+i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumerV1 pc = new ProduceConsumerV1();
        new Thread("P"){
            @Override
            public void run() {
                while(true)
                pc.producer();

            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while(true){
                    pc.consumer();
                }
            }
        }.start();
    }

}
