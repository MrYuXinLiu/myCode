package com.example.thread;

import java.util.concurrent.*;

/**
 * @Author: Lxy
 * @Date: 2019/2/21 20:51
 * 
 * 多线程创建的第四种方式
 */
public class MyThreadPoolDomo {
    public static void main(String[] args) {
        //线程池之最终版,自定义线程池
        /**
         * corePoolSize : 核心线程数
         * maximumPoolSize : 最大线程数
         * keepActiveTime : 存活时间 ,当空闲下来的时候达到这个时间,那么线程会被回收
         * TimeUnit : 时间单位
         * BlockingQueue : 阻塞队列
         * ThreadFactory : 线程工厂
         * RejectedExecutionHandler : 拒绝策略 默认使用AbortPolicy
         *
         * 拒绝策略之DiscardPolicy()
         *
         * DiscardPolicy : 当任务添加到线程池中被拒绝时, 线程池丢弃被拒绝的任务
         *
         *
         *
         */
        //创建自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,
                2,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        //创建线程
        try {
            for (int i = 1; i <=20 ; i++) {
                final int temp = i ;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行 \t" + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //记得要关闭
            threadPool.shutdown();
        }


        //线程池之最终版,自定义线程池
        /**
         * corePoolSize : 核心线程数
         * maximumPoolSize : 最大线程数
         * keepActiveTime: 当此线程达到多少秒之后不用,那么将会被回收,这里设置的是回收的时间
         * TimeUnit : 存活时间的单位
         * BlockingQueue : 阻塞队列
         * ThreadFactory : 线程工厂  使用默认的线程工厂
         * RejectedExecutionHandle : 拒绝策略  默认使用AbortPolicy
         *
         *
         * 拒绝策略之DiscardOldestPolicy()
         *
         *  DiscardOldestPolicy : 当任务添加到线程池中被拒绝时,线程池会放弃等待最旧的未处理任务,然后被拒绝的任务添加到等待队列
         *
         */


        /*//首先先创建自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,
                2,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());
        //创建线程
        try {
            for (int i =1 ; i <= 30 ; i++) {
                final int temp = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行任务"+temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //每次使用之后记得要还
            threadPool.shutdown();
        }*/

        //线程池之最终版,自定义线程池
        /**
         *
         * corePoolSize : 核心线程池数
         * maxmumPoolSize : 最大线程池数
         * keepAliveTime : 当空闲到达多少时间后回收线程
         * TimeUnit : 存活时间的单位
         * BlockingQueue : 阻塞队列
         * ThreadFactory : 线程工厂  使用默认的线程工厂
         * RejectedExecutionHandler : 拒绝策略  当达到最大线程池数+ 阻塞队列的数组长度, 那么会采用 拒绝策略
         *
         *
         * 拒绝策略之 CallerRunsPoliccy
         *   当任务添加到线程池中被拒绝时, 会在线程池当前正在运行的Thread线程池中处理被拒绝的任务  即 就是你的主线程 main 线程
         *
         */
        //首先先创建一个自定义线程池
        /*ExecutorService threadPool = new ThreadPoolExecutor(2,5,
                2,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        //创建线程
        try {
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //用完记得关闭连接哦
            threadPool.shutdown();
        }*/


        //线程池之最终版, 自定义线程池
        /**
         * corePoolSize : 核心线程池数
         * maximumPoolSize : 最大线程池数
         * keepAliveTime : 非核心线程池数多少时间不用会被回收掉
         * TimeUnit : 存活时间的单位
         * BlockingQueue : 阻塞队列
         * ThreadFactory : 线程池工厂,用默认的就OK
         * RejectedExecutionHandler : 当线程已满被拒时候做的处理方法 - 默认是AbortPolicy()
         *
         *
         * 拒绝策略之AbortPolicy() : 当超过maximumPoolSize + BlockingQueun 的数量的时候直接抛出异常 RejectedExecutionException
         *
         */
        // 先自定义一个线程池
        /*ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,3,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy()) ;
        try {
            for (int i = 1; i <=9 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }*/


        /*//线程池之三  创建可扩容的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            //先创建多线程看一下是否可以进行扩容
            for (int i = 1; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
*/



       /* //线程池之二  创建只有一个线程的线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //这里创建是个线程,看一下线程池是如何分配的
        try {
            for (int i = 1; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池连接
            threadPool.shutdown();
        }*/




        /*//线程池之一 创建有多少线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            //创建十个线程,看一下最大的线程数是不是设置的线程数
            for (int i = 1; i <=30 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"正在执行业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //用完线程池记得一定要还
            threadPool.shutdown();
        }*/

    }
}
