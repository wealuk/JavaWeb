package com.zhuihaikejixueyuan.dao.impl;

import com.zhuihaikejixueyuan.dao.OrgnizationDao;
import com.zhuihaikejixueyuan.pojo.Orgnization;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-24 17:00
 */
public class OrgnizationDaoImpl extends BaseDao implements OrgnizationDao {
    @Override
    public int addOrgnization(Orgnization orgnization) {
        String sql = "insert into orgnization(`massname`,`funtionaryname`,`major`,`createtime`,`member`) values(?,?,?,?,?)";
        return update(sql,orgnization.getMassname(),orgnization.getFuntionaryname(),orgnization.getMajor(),orgnization.getCreatetime(),orgnization.getMember());
    }

    @Override
    public int deleteOrgnizationByName(String name) {
        String sql = "delete from orgnization where massname = ?";
        return update(sql,name);
    }

    @Override
    public int updateOrgnization(Orgnization orgnization) {
        String sql = "update orgnization set `funtionaryname` = ?,`major`= = ?,`createtime` = ?,`member` = ? where massname = ?";
        return update(sql,orgnization.getFuntionaryname(),orgnization.getMajor(),orgnization.getCreatetime(),orgnization.getMember(),orgnization.getMassname());
    }

    @Override
    public List<Orgnization> qureyOrgnization() {
        String sql = "select `massname`,`funtionaryname`,`major`,`createtime`,`member` from orgnization";
        return queryForList(Orgnization.class,sql);
    }


}
