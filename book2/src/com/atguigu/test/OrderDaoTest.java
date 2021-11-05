package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2021-09-07 11:03
 */
public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
    }

    @Test
    public void queryMyOrders() {
        for(Order order : orderDao.queryMyOrders(1)){
            System.out.println(order);
        }
    }
}