package com.study.Order;

/**
 * 单机模式下的获取订单
 */
public class OrderSingle {
    public static void main(String[] args) {
        OrderGeneratorTool orderGeneratorTool = new OrderGeneratorTool();
        String number = orderGeneratorTool.creatOrderNumber();
        System.out.println(number);
    }
}
