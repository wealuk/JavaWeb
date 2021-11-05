package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-07-31 12:29
 */
public abstract class BaseDao {

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来执行：Insert/Update/Delete语句
     * @return  如果返回-1说明执行失败，返回其他表示影响的行数
     */
    public int update(String sql,Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }

        return -1;
    }

    /**
     * 返回一个JavaBean的sql语句
     * @param type 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 返回多个JavaBean的sql语句
     * @param type 返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object querySingleValue(String sql, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }
}
