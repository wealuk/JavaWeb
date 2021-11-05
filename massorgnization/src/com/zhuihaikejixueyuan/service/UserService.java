package com.zhuihaikejixueyuan.service;

import com.zhuihaikejixueyuan.pojo.User;

/**
 * @author shkstart
 * @create 2021-09-23 10:28
 */
public interface UserService {
    public void registUser(User user);

    public User login(User user);

    public boolean ifExitStudentNumber(String studentNumber);

    public int updatePassword(String studentnumber,String newpassword);

    public User ifExitUserByNumberAndOldPassword(String studentnumber,String oldpassword);
}
