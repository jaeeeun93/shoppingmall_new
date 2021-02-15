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
<h3>주문 상세</h3>
<div style="height:150px;"></div>
<form>
<table width=100% border=1>
	<tr>
		<fmt:parseDate value="${d.orderDate}" pattern ="yyyyMMddhhmmss" var="date"> </fmt:parseDate>
		<th colspan=2>주문일자 <input name="" value="<fmt:formatDate value='${date}' pattern='yyyy년 MM월 dd일'></fmt:formatDate>" readonly></th>
	</tr>
	
	<c:forEach var="list" items="${v}" varStatus="status">
			<td align=center><a href="/admin/item/info.do?code=${list.itemCode}"><input type="image" name="" src="/upload/${list.cartFile_s }" readonly></a></td>
			<td colspan=2 align=center>
				<input name="orderDate" value="${list.itemName}" readonly><br>
				<input name="itemPrice" value="${list.itemPrice}원" readonly><br>
			</td>
		</tr>
	</c:forEach>
	<th colspan=2>결제정보</th>
	<tr>
		<td>결제수단</td>
		<td>카카오페이</td>
	</tr>
	<tr>
		<td>총 결제금액</td>
		<td><fmt:formatNumber type='number' maxFractionDigits='3' value='${d.totalPrice}'/>원</td>
	</tr>
	
	
	<th colspan=2>받는사람 정보</th>
	<tr>
		<td>받는사람</td>
		<td><input name="" value="${d.cname}" readonly></td>
		
	</tr>
	<tr>
		<td>연락처</td>
		<td><input name="" value="${d.cphone}" readonly></td>
	</tr>
	<tr>
		<td>받는주소</td>
		<td>
			<input name="" value="${d.zipcode}" readonly><br>
			<input name="" value="${d.zip1}" readonly><br>
			<input name="" value="${d.zip2}" readonly><br>
			<input name="" value="${d.zip3}" readonly><br>
		</td>
		
	</tr>
	<tr>
		<td>배송요청사항</td>
		<td><input name="" value="${d.pick}" readonly></td>
	</tr>
	<tr>
		<td colspan=2 align="center"><button type="button" onclick="location.href='/order/orderCheck.do?id=<%=session_id %>'">주문목록 돌아가기</button></td>
	</tr>
</table>
</form>


</body>
</html>