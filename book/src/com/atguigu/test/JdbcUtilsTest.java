package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.Collections;

/**
 * @author shkstart
 * @create 2021-07-31 12:22
 */
public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils(){
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
        JdbcUtils.close(conn);
    }
}
