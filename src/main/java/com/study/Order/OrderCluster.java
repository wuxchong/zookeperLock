package com.study.Order;

import java.util.concurrent.CyclicBarrier;

/**
 * 集群模式下的获取订单
 */
public class OrderCluster {
    private static int current = 20;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(current);
        OrderService orderService = new OrderService();
        for (int i = 0; i < current; i++) {
            new Thread(orderService).start();

        }
    }
}
