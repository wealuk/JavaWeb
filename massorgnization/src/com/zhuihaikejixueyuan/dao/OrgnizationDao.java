package com.zhuihaikejixueyuan.dao;

import com.zhuihaikejixueyuan.pojo.Orgnization;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-24 16:56
 */
public interface OrgnizationDao {
    public int addOrgnization(Orgnization orgnization);

    public int deleteOrgnizationByName(String name);

    public int updateOrgnization(Orgnization orgnization);

    public List<Orgnization> qureyOrgnization();




}
