package com.zhuihaikejixueyuan.test;

import com.zhuihaikejixueyuan.dao.UserDao;
import com.zhuihaikejixueyuan.dao.impl.UserDaoImpl;
import com.zhuihaikejixueyuan.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2021-09-21 22:24
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void saveUser() {
        int i = userDao.saveUser(new User("avcd","1245678","1234567@qq.com"));
        System.out.println(i);
    }

    @Test
    public void queryByStudentNumber() {
        System.out.println(userDao.queryByStudentNumber("2021"));
    }

    @Test
    public void updatePasswordByStudentNumber() {
        System.out.println(userDao.updatePasswordByStudentNumber("2021","5201314"));
    }

    @Test
    public void queryByStudentNumberAndpPassword() {
        System.out.println(userDao.queryByStudentNumberAndpPassword("2021","5201314"));
    }
}