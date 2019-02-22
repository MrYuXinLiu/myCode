package com.example.thread;

/**
 * @Author: Lxy
 * @Date: 2019/2/22 23:18
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用
 *   有返回值,无返回值
 *
 *
 *
 *   铭记: 一定要多看看JAVA8 新特性!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 *   CompletableFuture 未来可完成的
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //无返回值的异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(" 无返回值的异步调用启动");
        });
        //有返回值的异步调用
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()->{
            System.out.println("异步有返回值调用");
            int age = 10/0;
            return 1024;
        });
        //未来是否可以办到,可以办到返回正确返回值,办不到,则返回异常
        Integer integer = completableFuture1.whenComplete((t, u) -> {
            System.out.println(t);
        }).exceptionally(e -> {
            System.out.println("exception:" + e);
            return 4444;
        }).get();

        System.out.println(integer);

    }

}
