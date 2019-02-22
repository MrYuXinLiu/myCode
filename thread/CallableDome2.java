package com.example.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lxy
 * @Date: 2019/2/21 20:25
 * 这里我们来说下阻塞与轮询
 */
public class CallableDome2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 以下代码我们来看下轮询是什么样的
         * 轮询:就是来回问,就比如你快递没到,那么问一下,没有到,下午又来问,而不是一直等
         *
         */
        //首先我们先创建一下未来工作
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            //这里睡一下,方便我们测试
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        },1024);
        //接下来我们创建一个线程,start一下
        new Thread(futureTask, "A").start();
        //接下来是轮询
        while(!futureTask.isDone()){
            System.out.println("await...");
        }
        System.out.println(Thread.currentThread().getName()+"\t task()");





        /**
         * 以下代码我们会发现当main方法执行时,必须等到未来工作做完之后才可以
         */
      /*  //首先我们先创建一个未来工作 返回1024
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            //为了测试方便,这里先让他睡一会
            try {
                TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        },1024);
        //接下来我们创建线程启动一下
        new Thread(futureTask, "A").start();
        //我们先来看一下阻塞
        System.out.println(futureTask.get());
        //我们再用主线程跑一下,看一下是否阻塞
        System.out.println(Thread.currentThread().getName()+"task()");*/


    }
}
