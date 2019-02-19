package com.example.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Lxy
 * 测试集合中那些是安全的
 */
public class NotSafeDemo {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 4));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }


    public static void setNotSafe() {

        Set<String> hashSet = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                hashSet.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(hashSet);

            }, String.valueOf(i)).start();
        }

    }


    public static void listNotSafe() {
        //判断几个线程是不安全的

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {

                list.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(list);

            }, String.valueOf(i)).start();

        }

    }


}
