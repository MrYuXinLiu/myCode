package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * 多线程第一种方法：
 * extends thread;
 * 多线程第二种方法：
 * implement Runnable;
 * 多线程 开启的第三种方法:
 * implement callable
 */

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("come in Hello callable");
        return 200;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //因为thread 的构造器只能上传Runnable对象，所以我们采取用Runnable 和 Callable对象共同的实现类 找个中间商
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("come in");
        }, 200);
        //通过FutureTask这个中间商然后放入多线程内
        new Thread(futureTask,"B").start();
        //调用FutureTask.get()获取callable中实现call方法的返回值
        Integer integer = futureTask.get();
        System.out.println(integer);
    }
}
