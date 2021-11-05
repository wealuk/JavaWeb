package com.zhuihaikejixueyuan.web;

import com.zhuihaikejixueyuan.pojo.Orgnization;
import com.zhuihaikejixueyuan.service.OrgnizationService;
import com.zhuihaikejixueyuan.service.impl.OrgnizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-24 17:45
 */
public class OrgnizationServlet extends BaseServlet{
    private OrgnizationService orgnizationService = new OrgnizationServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    protected void queryOrgnization(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Orgnization> orgnizations = orgnizationService.queryOrgnization();
        req.getSession().setAttribute("orgnizations",orgnizations);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
