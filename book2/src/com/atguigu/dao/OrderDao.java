package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-09-06 21:30
 */
public interface OrderDao {

    public int saveOrder(Order order);

    public List<Order> queryMyOrders(Integer userId);
}
