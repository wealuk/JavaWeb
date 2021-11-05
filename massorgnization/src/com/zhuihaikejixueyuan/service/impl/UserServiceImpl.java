package com.zhuihaikejixueyuan.service.impl;

import com.zhuihaikejixueyuan.dao.UserDao;
import com.zhuihaikejixueyuan.dao.impl.UserDaoImpl;
import com.zhuihaikejixueyuan.pojo.User;
import com.zhuihaikejixueyuan.service.UserService;

/**
 * @author shkstart
 * @create 2021-09-23 10:50
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryByStudentNumberAndpPassword(user.getStudentNumber(),user.getPassword());
    }

    @Override
    public boolean ifExitStudentNumber(String studentNumber) {
        if(userDao.queryByStudentNumber(studentNumber) != null){
            return true;
        }
        return false;
    }

    @Override
    public int updatePassword(String studentnumber, String newpassword) {
        return userDao.updatePasswordByStudentNumber(studentnumber, newpassword);
    }

    @Override
    public User ifExitUserByNumberAndOldPassword(String studentnumber, String oldpassword) {
        return userDao.queryByStudentNumberAndpPassword(studentnumber,oldpassword);
    }

}
