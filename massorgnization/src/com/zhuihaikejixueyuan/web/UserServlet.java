package com.zhuihaikejixueyuan.web;

import com.zhuihaikejixueyuan.pojo.User;
import com.zhuihaikejixueyuan.service.UserService;
import com.zhuihaikejixueyuan.service.impl.UserServiceImpl;
import com.zhuihaikejixueyuan.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author shkstart
 * @create 2021-09-23 12:26
 */
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentnumber = req.getParameter("studentnumber");
        String password = req.getParameter("password");

        User user = userService.login(new User(studentnumber, password, null));

        if(user == null){
            req.setAttribute("msg","学号或密码错误！");
            req.setAttribute("studentnumber",studentnumber);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String studentnumber = req.getParameter("studentnumber");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyToBean(req.getParameterMap(), new User());

        if(token != null && token.equalsIgnoreCase(code)){
            if(userService.ifExitStudentNumber(studentnumber)){
                req.setAttribute("msg","用户学号已经注册！");
                req.setAttribute("studentnumber",studentnumber);
                req.setAttribute("email",email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

            }else {
                userService.registUser(new User(studentnumber,password,email));

                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("studentnumber",studentnumber);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");

        if(userService.ifExitUserByNumberAndOldPassword(user.getStudentNumber(),oldpassword) != null){
            userService.updatePassword(user.getStudentNumber(),newpassword);
            req.getSession().removeAttribute("user");
            req.getRequestDispatcher("/pages/user/update_success.jsp").forward(req,resp);
        }else{
            req.setAttribute("msg","原密码错误！");
            req.getRequestDispatcher("/pages/user/updatepassword.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }
}
