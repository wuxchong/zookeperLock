package com.study.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单编号生成工具
 */
public class OrderGeneratorTool {
    private static int count = 0;
    public String creatOrderNumber() {
        count++;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return "获取订单编号为: " + dateFormat.format(new Date()) + '-' + count;
    }
}
