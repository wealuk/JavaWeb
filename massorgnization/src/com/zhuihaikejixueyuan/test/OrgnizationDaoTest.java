package com.zhuihaikejixueyuan.test;

import com.zhuihaikejixueyuan.dao.OrgnizationDao;
import com.zhuihaikejixueyuan.dao.impl.OrgnizationDaoImpl;
import com.zhuihaikejixueyuan.pojo.Orgnization;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author shkstart
 * @create 2021-09-24 17:14
 */
public class OrgnizationDaoTest {
    OrgnizationDao orgnizationDao = new OrgnizationDaoImpl();

    @Test
    public void addOrgnization() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        orgnizationDao.addOrgnization(new Orgnization("膜蛤社","董门","软件工程",dateFormat.parse("2020-8-8"),"刘导,易李"));
    }

    @Test
    public void deleteOrgnizationByName() {
        orgnizationDao.deleteOrgnizationByName("反二次元社");
    }

    @Test
    public void updateOrgnization() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        orgnizationDao.updateOrgnization(new Orgnization("反二次元社","门","软件工程",dateFormat.parse("2020-8-8"),"刘导,易李"));
    }

    @Test
    public void qureyOrgnization() {
        System.out.println(orgnizationDao.qureyOrgnization());
    }
}