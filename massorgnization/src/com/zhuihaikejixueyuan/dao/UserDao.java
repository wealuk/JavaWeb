package com.zhuihaikejixueyuan.dao;


import com.zhuihaikejixueyuan.pojo.User;

/**
 * @author shkstart
 * @create 2021-09-21 22:04
 */
public interface UserDao{
    /**
     * 保存用户
     * @param user
     * @return
     */
    public int saveUser(User user);

    public User queryByStudentNumber(String studentNumber);

    public User queryByStudentNumberAndpPassword(String studentNumber,String password);

    public int updatePasswordByStudentNumber(String studentNumber,String password);
}
