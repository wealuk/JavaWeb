package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    //操作数据库时，需要一系列操作一起成功或一起失败。所以需要事务。而事务需要都是同一个连接对象。
    //而一个线程中，ThreadLocal可以保存一个连接对象，只要是在一个线程，就能共享一个连接对象。
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
//
//        Connection conn = null;
//
//        try {
//            conn = dataSource.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return conn;  需要用ThreadLocal进行更新，确保线程安全

        Connection conn = conns.get();

        if(conn == null){
            try {
                conn = dataSource.getConnection();

                conns.set(conn);//保存到ThreadLocal对象中，供后面的jdbc使用。

                conn.setAutoCommit(false);//设置为手动管理事务
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){//如果不等于null,说明之前用过连接，操作过数据库

            try {
                connection.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接，释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则会出错。(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();

    }

    /**
     * 回滚事务
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){//如果不等于null,说明之前用过连接，操作过数据库

            try {
                connection.rollback();//回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接，释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则会出错。(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }



//    /**
//     * 关闭连接，放回数据库连接池
//     * @param conn
//     */
//    public static void close(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}