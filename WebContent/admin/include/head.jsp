<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
String session_id = (String)session.getAttribute("id");
String session_name = (String)session.getAttribute("name");
String session_level = (String)session.getAttribute("level");

if(session_level != null && session_level.equals("10")){
	
}else{
%>
	<script>
		alert("!!!!!!!");
		location.href="/";
	</script>
<%		
}
%>

Session_id : <%=session_id %><br>
Session_name : <%=session_name %><br>
session_level : <%=session_level%><br>
<hr>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC패턴 관리자</title>
</head>
<body>

<table width=100%>
	<tr>
		<td><a href="/">로고</a></td>
		<td align=right>
		<%if(session_level != null && session_level.equals("10")){ %>
			<a href="/admin/index.jsp">[관리자]</a>
		<%} %>
		<%if(session_id != null){ %>
			<a href="/member/logout.do">[로그아웃]</a>
			<a href="/member/member_modify.do">[회원수정]</a>
		<%}else{ %>	
			<a href="/member/login.jsp">[로그인]</a>
			<a href="/member/join.jsp">[회원가입]</a>
		<%} %>
		</td>
	</tr>
</table>
<table width=100%>
	<tr>
		<td align=center><a href="/admin/member/list.do">회원관리</a></td>
		<td align=center><a href="/admin/OrderControll.do">주문내역</a></td>
		<td align=center><a href="/admin/item/adminItemList.do">상품관리</a></td>
		<td align=center><a href="/admin/Inquire/InquireReceive.do">상품문의</a></td>
		<td align=center><a href="/admin/sales/TotalSale.do">매출현황</a></td>
	</tr>
</table>
<hr>