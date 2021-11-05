package com.zhuihaikejixueyuan.service;

import com.zhuihaikejixueyuan.pojo.Orgnization;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-24 17:40
 */
public interface OrgnizationService {
    public List<Orgnization> queryOrgnization();

    public void addOrgnization(Orgnization orgnization);

    public void deleteOrgnization(Orgnization orgnization);

    public void updateOrgnization(Orgnization orgnization);
}
