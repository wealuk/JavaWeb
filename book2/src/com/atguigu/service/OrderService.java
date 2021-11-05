package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-07 11:26
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> myOrders(Integer userId);

    public List<OrderItem> orderDetails(String orderId);
}
