package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2021-09-07 11:08
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {

        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"c从入门到精通",2,new BigDecimal(200),new BigDecimal(400),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"python从入门到精通",1,new BigDecimal(1),new BigDecimal(100),"1234567890"));
    }

    @Test
    public void queryOrderDetailByOrderId() {
        for(OrderItem orderItem : orderItemDao.queryOrderDetailByOrderId("16310127696201")){
            System.out.println(orderItem);
        }
    }
}