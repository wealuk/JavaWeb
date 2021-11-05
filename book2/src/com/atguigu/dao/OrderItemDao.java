package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-07 10:57
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderDetailByOrderId(String orderId);

}
