package com.example.thread;


import java.util.concurrent.CountDownLatch;

/**
 * JUC中的方法之-CountDownLatch (倒数计数器)
 * 当设置的数量下降为零时才启动main后面的方法
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //任务：当六国被灭之后秦国才可以一统华夏
        //1：创建倒数计数器
        CountDownLatch latch = new CountDownLatch(6);
        //2：一共六个国家,此时需要开启六个线程
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"被灭");
                //这个倒数计时器开始去递减，调用一次count -1 ;
                latch.countDown();
            }, CountDownLatchEnum.foreach(i).getMessage()).start();
        }
        //count 如果不满足 = 0  那么，不会执行下面的程序;
        latch.await();
        System.out.println(Thread.currentThread().getName()+"一统华夏");

    }

}
