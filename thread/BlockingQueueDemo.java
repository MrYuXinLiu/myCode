package com.example.thread;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.concurrent.*;

/**
 * @Author: Lxy
 * @Date: 2019/2/21 19:00
 *
 * 阻塞队列
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 阻塞队列中超时不候的Api
         *
         * offer()  pull() ;
         *
         *
         *
         * offer(data,TimeOut,TimeUnit)  当达到队列长度时,那么会根据timeOUT 等侯一段时间,如果可以等到就等,等不到就返回false;
         *
         * poll (timeout,timeUnit)  当想移除的时候发现队列里面没有东西,那么会根据timeOut的时间等你,如果时间过去还没有值,那么就返回null;
         */
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("B", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("C", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("D", 2L, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));


        /**
         * 阻塞队列之SynchronousQueue 同步式阻塞队列
         *
         *  SynchronousQueue 不存储元素的阻塞列表  ,也即单个元素的队列
         *
         *
         *
         * 阻塞的APi
         *
         * put 插入  take 移除
         *
         *
         *  这里因为是同步队列,所以到时候测试请用指定长度的ArrayBlockingQueue测试
         *
         *
         * put  当插入的时候超过数组长度,那么会阻塞住,知道完成put或者响应中断的时候才会中止此操作,否则会一直阻塞
         *
         *
         * take 当想获取的值没有在此数组的长度之内的话,那么会阻塞住,直到你有值能take出来的时候才会中止,否则只能中止此操作
         */
//        BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();
//        blockingQueue.put("A");
//        blockingQueue.put("B");
//
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());


        /**
         *
         *
         * 阻塞队列之 LinkedBlockingQueue
         * 默认以Integer的最大值为初始长度
         *
         *
         *
         * 因为这里用的是LinkedBlockingQueue 数组长度为Integer的最大值,所以统一返回的true 如果此时用的是ArrayBlockingQueue 的话
         * 指定长度,那么超出指定的长度,那么回返回false;
         *
         *
         * 返回特殊值的Api
         *
         *
         * offer 添加   poll 移除   peek  检查
         *
         *  offer 当超出定义的队列长度时,出返回特殊值false;
         *
         *  poll  当超出定义的队列长度时,返回特殊值null
         *
         *  peek  若当前没有值的话会返回null;
         *
         *
         */
//        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);
//        System.out.println(blockingQueue.offer("A"));
//        System.out.println(blockingQueue.offer("B"));
//        System.out.println(blockingQueue.offer("C"));
//        System.out.println(blockingQueue.offer("D"));
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//
//        System.out.println(blockingQueue.peek());


        /**
         * ArrayBlocking阻塞队列
         *  指定队列的长度的阻塞队列
         *
         *
         *
         *
         * 抛出异常的Api
         * add 添加  remove删除  element检查
         *
         *
         *  add : 当超出你设定的数组最大的长度的时候会抛出异常
         *
         *  remove : 当你数组删除的次数超过数组总长度会抛出异常
         *
         *  element : 检查时如果此数组中没有任何元素,那么会抛出异常
         */
//        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
//        queue.add("1");
//        queue.add("2");
//        queue.add("3");
//       // queue.add("1");
//        queue.remove();
//        System.out.println(queue.element());
//        queue.remove();
//        queue.remove();
//        // queue.remove();

    }
}
