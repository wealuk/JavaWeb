<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
		<div id="login_header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">社团管理</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>修改密码</h1>
<%--								<a href="pages/user/regist.jsp">立即注册</a>--%>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${ empty requestScope.msg ? "请输入原密码和新密码":requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="updatePassword" />
									<label>用户原密码：</label>
									<input class="itxt" type="password" placeholder="请输入原密码"
										   autocomplete="off" tabindex="1" name="oldpassword"/>
									<br />
									<br />
									<label>用户新密码：</label>
									<input class="itxt" type="password" placeholder="请输入新密码"
										   autocomplete="off" tabindex="1" name="newpassword" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>