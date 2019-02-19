package com.example.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Lxy
 * juc 中的第二個工具 CyclicBarrier (循环屏障)
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //先去创建一个CyclicBarrier(这里面是要到达的峰值,Runnable接口)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{ System.out.println("葫芦娃救爷爷,傻逼，能不能团"); });
        for (int i = 1; i <=7 ; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"：我要去救爷爷");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, CyclicBarrierEnum.foreach(i).getMessage()).start();
        }
    }
}
