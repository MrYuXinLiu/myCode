package com.example.thread;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * * 题目：多线程8锁
 * 1 一般访问，请问先打印短信还是邮件？  *随机
 * 2 短信暂停4秒钟，请问先打印短信还是邮件？ *短信
 * 3 新增普通开机方法， 请问先打印短信还是开机？*开机
 * 4 有两部手机，请问先打印短信还是邮件？ *邮件
 * 5 静态同步方法，1部手机，请问先打印短信还是邮件？*短信
 * 6 静态同步方法，2部手机，请问先打印短信还是邮件？*短信
 * 7 一个普通同步方法，一个静态同步方法，1部手机，请问先打印短信还是邮件？*邮件
 * 8 一个普通同步方法，一个静态同步方法，2部手机，请问先打印短信还是邮件？*邮件
 */
public class Lock8 {

    /**
     * Lock的资源类
     */


    public static void main(String[] args) throws InterruptedException {
        //先创建一个资源类
        LockResource lockResource = new LockResource();
        LockResource lockResource2 = new LockResource();
        //创建发送短信的线程
        new Thread(() -> {
            lockResource.sendSMS();
        }, "A").start();
        //Thread.sleep(200);
        //创建发送邮件的线程
        new Thread(() -> {
            lockResource.sendEmail();
            //lockResource.openPhone();
        }, "B").start();


    }
}

class LockResource {
    public static synchronized void sendSMS() {
        try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + "sendSMS");
    }

    public  synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "sendEmail");
    }

    public void openPhone() {
        System.out.println(Thread.currentThread().getName() + "openPhone");
    }
}
