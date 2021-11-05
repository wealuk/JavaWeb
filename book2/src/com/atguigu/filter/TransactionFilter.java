package com.atguigu.filter;

import com.atguigu.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 所有service方法都需要事务的保证，确保同时成功或者失败，避免数据库进行一半。
 * 我们可以使用一个Filter给所有的service方法来加上try-catch，而不需要一个个去加。
 * 原理是：filterChain.doFilter()之后，就会跳转运行servlet程序，而servlet直接调用业务(service)方法，所以
 * 就相当于Filter里间接调用了service方法。也就是直接在doFilter()给filterChain.doFilter()绑定try-catch就行了。
 * 注意：前面所有嵌套的什么dao，baseservlet的异常都需要进行抛，留到处理service时，一起使用，否则异常达不到service
 *      就无法实现提交或者回滚
 * @author shkstart
 * @create 2021-09-11 13:13
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);//这个就相当于间接调用了service方法

            JdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat统一展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
