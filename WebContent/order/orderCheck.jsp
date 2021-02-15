<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div style="height:150px;"></div>
<h3>주문/배송조회</h3>

<table width=100% height=300 border=1 cellspacing=0>
<c:set var="test" value="<%=total2 %>"/>
<c:choose>
<c:when test="${test == 0 }">
<tr>
	<td align="center">주문한 상품이 없습니다.</td>
</tr>		
</c:when>
<c:otherwise>
	<c:forEach var="list" items="${p}" varStatus="status">
	<c:set var="list2" value="${v[status.index]}"/>
	<fmt:parseDate value="${list.orderDate}" pattern ="yyyyMMddhhmmss" var="date"> </fmt:parseDate>
	<fmt:parseDate value="${list.deliveryDate}" pattern ="yyyyMMddhhmmss" var="date2"> </fmt:parseDate>
		<tr>
			<td colspan=3><input name="itemPrice" value="주문일 <fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly> <a href="/order/orderDetail.do?orderId=${list.orderId }"><span style="color:#4285f4;">주문 상세보기></span></a></td>
		<tr>	
			<td align=center><a href="/admin/item/info.do?code=${list.itemCode}"><input type="image" name="" src="/upload/${list.cartFile_s }" readonly></a></td>
			<td align=center>
				<input name="orderDate" value="${list.itemName}" readonly><br>
				<input name="itemPrice" value="${list.itemPrice}원" readonly><br>
			</td>
			<td align=center>
				<b>${list.delivery }</b><br>
			<c:choose>
			<c:when test="${list.deliveryDate == '' }">
				<b>도착미정</b><br>
			</c:when>
			<c:when test="${list.delivery == '배송완료' }">
				<b><fmt:formatDate value='${date2}' pattern='MM/dd'></fmt:formatDate> 도착</b><br>
			</c:when>
			<c:otherwise>
				<b><fmt:formatDate value='${date2}' pattern='MM/dd'></fmt:formatDate> 도착예정</b><br>
			</c:otherwise>
			</c:choose>
				<a href="/review/reviewList.do?id=<%=session_id%>"><span style="color:#4285f4;">구매후기 쓰기</span></a><br>
			</td>
		</tr>
	</c:forEach>
</c:otherwise>
</c:choose>
</table>
</form>
</body>
</html>