package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2021-07-31 14:21
 */
public class UserServiceTest {

    UserService us = new UserServiceImpl();

    @Test
    public void registUser() {
        us.registUser(new User(null,"lj168","666666","lj168@qq.com"));
        us.registUser(new User(null,"HJH11","88888888","HJH168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(us.login(new User(null,"HJH11","88888888","HJH168@qq.com")));
    }

    @Test
    public void existsUsername() {
        if(us.existsUsername("HJH11")){
            System.out.println("存在用户");
        }else{
            System.out.println("不存在");
        }
    }
}