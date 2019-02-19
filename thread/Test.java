package com.example.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lxy;
 * 简单的多线程售票系统
 *
 */
public class Test {


    //多线程
    // 1. 几个线程   操作如何    资源库
    public static void main(String[] args) {
        // 先创建一个资源库
        resource resource = new resource();
        // 开启三个线程
        new Thread(() -> {
            for (int i = 0; i < 40; i++) resource.save();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) resource.save();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) resource.save();
        }, "c").start();

        new Thread(() -> {
            for (int i = 0; i <2; i++) resource.save(); ;
        }, "线程名字").start();
    }


}

class resource {
    //1 . 售票员的数量
    private int personCount = 3;
    //2 . 一共多少张票
    private int ticket = 30;
    //3 . 避免并发,所以创建一个Lock;
    private Lock lock = new ReentrantLock();

    public void save() {
        lock.lock();
        try {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "售出第" + ticket-- + "张票,还有" + ticket + "张票");
            }
        } finally {
            lock.unlock();
        }
    }

}
