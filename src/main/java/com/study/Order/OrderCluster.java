package com.study.Order;

import java.util.concurrent.CyclicBarrier;

/**
 * 集群模式下的获取订单
 */
public class OrderCluster {
    private static int current = 20;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(current);
        for (int i = 0; i < current; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                OrderGeneratorTool orderGeneratorTool = new OrderGeneratorTool();
                String number = orderGeneratorTool.creatOrderNumber();
                System.out.println(Thread.currentThread().getName() + "  " + number);
            }).start();

        }
    }
}
