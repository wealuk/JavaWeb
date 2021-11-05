package com.zhuihaikejixueyuan.service.impl;

import com.zhuihaikejixueyuan.dao.OrgnizationDao;
import com.zhuihaikejixueyuan.dao.impl.OrgnizationDaoImpl;
import com.zhuihaikejixueyuan.pojo.Orgnization;
import com.zhuihaikejixueyuan.service.OrgnizationService;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-24 17:43
 */
public class OrgnizationServiceImpl implements OrgnizationService {
    private OrgnizationDao orgnizationDao = new OrgnizationDaoImpl();

    @Override
    public List<Orgnization> queryOrgnization() {
        return orgnizationDao.qureyOrgnization();
    }

    @Override
    public void addOrgnization(Orgnization orgnization) {

    }

    @Override
    public void deleteOrgnization(Orgnization orgnization) {

    }

    @Override
    public void updateOrgnization(Orgnization orgnization) {

    }
}
