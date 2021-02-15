<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URI"%>
<%@ include file="/include/head.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<fmt:parseDate value="${date}" pattern ="yyyyMM" var="date2"> </fmt:parseDate>

<h3><fmt:formatDate value='${date2}' pattern='yyyy년 MM월'></fmt:formatDate> 매출현황</h3>

<form>
<table width=100% border=1>
	<tr>
		<td>주문번호</td>
		<td>주문자 아이디</td>
		<td>주문자 이름</td>
		<td>주소</td>
		<td>주문자 연락처</td>
		<td>주문날짜</td>
		<td>주문합계</td>
		<td>쿠폰</td>
		<td>카카오페이</td>
		<td>받는 사람</td>
		<td>받는 사람 번호</td>
		<td>배송여부</td>
		<td>배송선택사항</td>
	</tr>
	
<c:choose>
<c:when test="${count == 0 }">
<tr>
	<td colspan=13 align=center>자료가 없습니다.</td>
</tr>		
</c:when>
<c:otherwise>
<c:forEach var="list" items="${day}">	
	<tr>
		<td>${list.orderUid}</td>
		<td>${list.id }</td>
		<td>${list.name }</td>
		<td>${list.zipcode }${list.zip1 }${list.zip2 }${list.zip3 }</td>
		<td>${list.phone }</td>
		<td>${list.orderDate }</td>
		<td>${list.totalPrice }</td>
		<td>${list.coupon }</td>
		<td>카카오페이</td>
		<td>${list.cname }</td>
		<td>${list.cphone }</td>
		<td>${list.delivery }</td>
		<td>${list.pick }</td>
	</tr>	
</c:forEach>
</c:otherwise>
</c:choose>

</table>
</form>

<table border=1>
	<tr>
		<td><fmt:formatDate value='${date2}' pattern='yyyy년 MM월'></fmt:formatDate> 총 매출액</td>
		<td>${sum }원</td>
	</tr>
</table>
</body>


</html>