package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lxy
 */
public class ThreadWaitNotifyDemo {

//    题目：现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
//            * 实现交替，来10轮，变量初始值为零。
//            *
//            * 1 高内聚低耦合前提下，线程   操作      资源类
// * 2 判断+干活+通知
// * 3 避免虚假唤醒，线程判断用while
    public static void main(String[] args) {
        ThreadResolve threadResolve = new ThreadResolve();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    threadResolve.increment();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    threadResolve.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            try {
                threadResolve.increment();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                threadResolve.decrement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "D").start();
    }

    /**
     * 这里运用lock 和condition 的方法
     */
    static class ThreadResolve {
        private int num = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        //增量
        public void increment() throws Exception {
            //加锁
            lock.lock();
            //1 先判断
            try {
                while (num != 0) {
                    condition.await();
                }
                //2  干活
                num++;
                System.out.println(Thread.currentThread().getName()+"\t\t"+num);
                //3  唤醒
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void decrement() {
            //加锁
          lock.lock();
          try {
              //判断
                while(num == 0 ){
                    condition.await();
                }
              //业务实现
              num--;
                // 唤醒
              condition.signalAll();
              System.out.println(Thread.currentThread().getName()+"\t\t"+num);
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
        }

    }


    /**
     * sychronized 锁， if 的话只能进行一次条件判断，所以在wait的时候 if 的话 就不再进行两次判断
     * 容易导致多加或者多减，所以这里采用while

     static class ThreadResolve{
     private int num  = 0;

     public synchronized void increment() throws Exception{
     try {
     while (num != 0 ){
     this.wait();
     }
     num++;
     System.out.println(Thread.currentThread().getName() +"\t\t" + num);
     notifyAll();
     } catch (Exception e) {
     e.printStackTrace();
     } finally {
     }
     }

     public synchronized void decrement() throws  Exception{
     try {
     while(num == 0 ){
     this.wait();
     }
     num--;
     System.out.println(Thread.currentThread().getName()+"\t\t" +num);
     notifyAll();
     } catch (Exception e) {
     e.printStackTrace();
     } finally {
     }
     }
     }*/

}
