<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.studentNumber}</span>光临本站</span>
    <a href="pages/user/updatepassword.jsp">修改密码</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>