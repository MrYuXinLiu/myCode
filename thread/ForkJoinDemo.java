package com.example.thread;

/**
 * @Author: Lxy
 * @Date: 2019/2/22 22:50
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 创建分支连接工作
 * 首先我们需要实现RecursiveTask 父类 因为forkJoinPool 需要用到同时实现的类
 */

class MyTask extends RecursiveTask<Integer>{
    // 私有的变量 final 每个人最大只能计算10个值;
    private static final Integer  ADJUST_VALUE = 10 ;
    // 这是初始值
    private int begin;
    //这是结束值
    private int end;
    //返回汇总的结果
    private int result;

    //构造器 为了给外界提供接口,这就相当于一个资源类


    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //实现父类的抽象方法计算
    @Override
    protected Integer compute() {

        /**
         * 判断最大值减最小差是否是一个人的工作量,如果是,就自己完成,如果不行,跳过
         */
        if (end - begin <= ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result = result + i ;
            }
        }else{
            // 二分法, 从中间砍开
            int middle = (begin +end)/2;
            //然后再把任务分配下去
            MyTask task1 = new MyTask(begin,middle);
            MyTask task2 = new MyTask(middle+1,end);
            //这里开始分支,分开计算,不用同步,要不然浪费时间
            task1.fork();
            task2.fork();
            //这里把task1 + task2 所做完任务得到的数据进行汇总
            result = task1.join()+task2.join();
        }
        //最后把汇总得到的数据返回出去
        return result;
    }
}


/**
 * 题目: 递归相加 分支开始计算, 每个人只能计算十个数的相加,最后进行汇总
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //首先把资源类创建出来
        MyTask task = new MyTask(0,100);

        //然后创建分支合并池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());
    }
}
