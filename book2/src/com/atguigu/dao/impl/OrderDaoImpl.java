package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-06 21:34
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql = "select `order_id` orderId , `create_time` createTime , `price` , `status` , `user_id` userId from t_order where `user_id` = ?";
        return queryForList(Order.class,sql,userId);
    }

}
