<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first jsp page</title>
</head>
<body>
	<h1>您好！</h1>
	<%
		java.util.Date date = new java.util.Date();
		out.println("当前时间是："+date.toLocaleString());
	%>
	<div id = "data">
		<table id="datalist">
			<tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
        	</tr> 
        	<c:forEach items="${adminlist}" var="admin">
        	<tr>
        	<td>${admin.name}</td>
            <td>${admin.account}</td>
            <td>${admin.phone}</td>
            <td>${admin.email}</td>
        	</c:forEach>
		</table>
	</div>
</body>
</html>