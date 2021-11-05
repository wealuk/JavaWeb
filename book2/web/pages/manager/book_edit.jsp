<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color: #ff0000;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>

			<%-- 静态包含 manager管理模块的菜单  --%>
			<%@include file="/pages/common/manager_menu.jsp"%>


		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
			<%--由于该页面即要实现添加，又要实现修改，所以需要将value的值动态化。
				有三种解决方法：
					1.在book_manager.jsp下给修改和添加的超链接添加一个method参数(add,update),value="${param.method}"
					2.添加是不需要id参数，修改有id参数。根据有无id，value="${empty param.id?"add":"update"}"
					3.添加没有经过servlet,而修改通过了一次servlet程序，根据req中是否有book值。
						value="${empty requestScope.book?"add":"update"}"
			--%>
<%--				<input type="hidden" name="action" value="${param.method}">--%>
<%--				<input type="hidden" name="action" value="${empty param.id?"add":"update"}">--%>
				<input type="hidden" name="action" value="${empty requestScope.book?"add":"update"}">
<%--				没有id传上去，update语句无法执行成功，因为null不能赋值给sql语句中的id--%>
				<input type="hidden" name="id" value="${requestScope.book.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>


		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>