package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-07 18:37
 */
public class OrderServlet extends  BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取UserId
        User loginUser = (User) req.getSession().getAttribute("user");

        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;//登录界面时，后面代码已经不需要执行
        }

        Integer userId = loginUser.getId();
        //调用orderService.createOrder(Cart,UserId)生成订单
        String orderId = orderService.createOrder(cart, userId);

//        req.setAttribute("orderId",orderId);
        //请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

        req.getSession().setAttribute("orderId",orderId);

        //请求转发会重复添加，故使用请求重定向
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 查询我的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //登陆后可以获取登录用户的id
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.myOrders(user.getId());
        //查到的数据保存的session中
        req.getSession().setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.orderDetails(orderId);
        req.getSession().setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        //这里跳转界面没有写完，需要一个jsp页面用于展示订单详情

    }
}
