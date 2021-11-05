package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
/*
    与book相比，该book1对其做了大量的代码优化：
    1.将所有的html文件修改为了jsp文件，并将内容中的.html修改为了.jsp。便于对客户端的响应
    2.将重复使用的<base/>和css、js代码都封装到一个新的jsp文件，使用时只需<%@ include file=''%>调用即可
    3.有了jsp，就可以进行表单提交失败的错误回显
    4.通过给登录、注册的表单的隐藏域<input type="hidden" name="action" value="login/regist"/>来
      合并两个Servlet成一个。同时可以优化以if-else为基础的login/regist.equals(req.getParamter("action"))
      的大量if-else语句，即以反射为基础，通过String action = req.getParameter("action")拿到对应的业务方法名
      ，(设置的value要跟java中的方法名一致),再通过
      this.getClass().getDeclaredMethod(action,httpServletRequst.class,httpServletRespon.class)拿到Method方法
      通过method.invoke(this,req,resp)调用对应的方法。这样就不需要一个业务就去加一个if-else，直接写方法就可以
      通过反射直接调用。当然，因为每一个业务都要调用同样的方法，所以我们将其写到一个父类用于继承。
      5.注册用户需要调用大量getParamter()调获取数据，再赋给构造器，所以我们可以导入BeanUtils、logging两个
        jar包。而对于其操作，我们一般封装到一个工具类中。BeanUtils的核心就是BeanUtils.populate(bean,Map)
        ,第一个参数为bean对象比如User user = new User();的user，第二个参数为包含所有参数的Map集合，我们通过
        req.getParamterMap()获取。本质就是将Map中的参数遍历出来后一个个通过user的set属性方法来设置好。(与
        user也就是bean对象的set方法有关)最后return user(bean),所以用到时就是User user = 封装的工具类.方法(req,map)
        在定义了泛型的情况下就可以直接一行代码赋值user了。
 */

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁Session中的用户登录信息(或者销毁Session)
        req.getSession().invalidate();
        //2.重定向到首页或登录页面
        resp.sendRedirect(req.getContextPath());

    }

    /**
     * 处理登录的功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户或密码错误！");
            req.setAttribute("username", username);
            //   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            //保存用户登录的信息到Session域中
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    /**
     * 处理注册的功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取Session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (token != null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //      可用
//                调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        //调用userService.existUsername()
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);


    }



    }
