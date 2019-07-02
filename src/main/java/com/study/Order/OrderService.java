package com.study.Order;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderService implements Runnable{

    OrderGeneratorTool orderGeneratorTool = new OrderGeneratorTool();

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        getNumber();
    }

    private void getNumber() {
        lock.lock();
        String num = orderGeneratorTool.creatOrderNumber();
        System.out.println(Thread.currentThread().getName() + num);
        lock.unlock();
    }
}
