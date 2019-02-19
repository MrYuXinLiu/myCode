package com.example.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Lxy
 *
 * juc 中的第三个工具   semaphore （信号灯）
 * 简称就是多份资源被多个线程疯抢
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //先去创建工具Semaphore(一共几份资源)
        Semaphore semaphore = new Semaphore(2);
        //任务：模拟停车场,一共两个停车位,有六辆车
        //1：先创建出六个线程
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //获得一个信号
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢占了车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //记得抢占完资源后一定要还哦
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }


    }
}
