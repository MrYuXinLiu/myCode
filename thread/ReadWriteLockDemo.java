package com.example.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Lxy
 * @Date: 2019/2/21 18:24
 * 读写锁
 *
 *
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 * 小总结：
 *          读-读能共存
 *          读-写不能共存
 *          写-写不能共存
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {
        //先创建资源类
        MyCache myCache = new MyCache();

        //创建四个线程
        for (int i = 1; i <=5 ; i++) {

            final int temp = i;
            //先创建两个线程负责写操作
            new Thread(() -> {
                myCache.put(""+temp,temp+"");
            }, String.valueOf(i)).start();
        }


        for (int i = 1; i <=5 ; i++) {
            final int temp = i;
            new Thread(() -> {

                myCache.get("" + temp);
            }, String.valueOf(i)).start();
        }

    }
}

/**
 * 多线程读同一份资源,
 *  一读一写 yes
 *  两写   no
 *  两读  yes
 *
 *  读写锁可以同时进行读,但是不能进行同时写
 */
class MyCache {
    //任务,模拟map中的put get 方法
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private volatile Map<String, Object> map = new HashMap<>();
    /**
     * 接下来我们用一下读写锁试验一下
     */
    //首先是加读写锁的Put方法
    public void put(String key ,Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写"+key);
            //效果不明显,睡一会吧
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写完成"+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }
    //接下来是加了读写锁的get方法
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读" + key);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完成" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }



//    /**
//     * 通过不加锁我们会发现当上一个线程还没有写完的时候其余线程开始加塞
//     * @param key
//     * @param value
//     */
//    //不调用读写锁的put 方法
//    public void put(String key, String value) {
//        System.out.println(Thread.currentThread().getName() + "正在写" + key + "操作");
//        map.put(key, value);
//        System.out.println(Thread.currentThread().getName() + "写完成" + key + "操作");
//    }
//    //不调用读写锁的get 方法
//    public Object get(String key){
//        System.out.println(Thread.currentThread().getName() + "正在读" + key + "操作");
//        Object resolt = map.get(key);
//        System.out.println(Thread.currentThread().getName() + "读完成" + resolt+"操作");
//
//        return resolt;
//    }


}













