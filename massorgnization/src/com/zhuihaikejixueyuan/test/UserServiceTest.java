package com.zhuihaikejixueyuan.test;

import com.zhuihaikejixueyuan.pojo.User;
import com.zhuihaikejixueyuan.service.UserService;
import com.zhuihaikejixueyuan.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2021-09-23 11:43
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User("2333","2333","2333@qq,com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User("2021","5201314","")));
    }

    @Test
    public void ifExitStudentNumber() {
        System.out.println(userService.ifExitStudentNumber("2021"));
    }
}