package com.zhuihaikejixueyuan.dao.impl;

import com.zhuihaikejixueyuan.dao.UserDao;
import com.zhuihaikejixueyuan.pojo.User;

/**
 * @author shkstart
 * @create 2021-09-21 22:11
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(`studentnumber`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getStudentNumber(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryByStudentNumber(String studentNumber) {
        String sql = "select `studentnumber`,`password`,`email` from user where studentnumber = ?";
        return queryForOne(User.class,sql,studentNumber);
    }

    @Override
    public User queryByStudentNumberAndpPassword(String studentNumber, String password) {
        String sql = "select `studentnumber`,`password`,`email` from user where studentnumber = ? and password = ?";
        return queryForOne(User.class,sql,studentNumber,password);
    }

    @Override
    public int updatePasswordByStudentNumber(String studentNumber, String password) {
        String sql = "update user set password = ? where studentnumber = ?";
        return update(sql,password,studentNumber);
    }
}
