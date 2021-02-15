<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/admin/include/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table width=100% heigth=700 border=1>
<c:forEach var="list" items="${msg}">
<fmt:parseDate value="${list.send_date}" pattern ="yyyyMMddhhmmss" var="date"> </fmt:parseDate>
	<tr>
		<td>보낸 사람 : ${list.send_id}</td>
	</tr>
	<tr>
		<td>보낸 시간 : <fmt:formatDate value='${date}' pattern='yyyy/MM/dd hh:mm'></fmt:formatDate></td>
	</tr>
	<tr>
		<td>${list.content}</td>
	</tr>
	<tr>
		<td><a onclick="location.href='/admin/Inquire/SendForm.jsp?id=${list.send_id}';" style="cursor: pointer">답장하기</a></td>
	</tr>
</c:forEach>
</table>
<button onclick="location.href='/admin/InquireReceive.do';">뒤로</button>

<%@include file="/admin/include/foot.jsp"%>