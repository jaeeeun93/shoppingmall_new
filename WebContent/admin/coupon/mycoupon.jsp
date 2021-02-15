<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div style="height:150px;"></div>
<table border=1 width=100% height=700>
<tr>
		<td colspan=7 align=center style="font-weight:bold; background-color:lightgray;">쿠폰리스트</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold">쿠폰명</td>
		<td align="center" style="font-weight:bold">발급날짜</td>
		<td align="center" style="font-weight:bold">유효기간</td>
		<td align="center" style="font-weight:bold">사용여부</td>
		<td align="center" style="font-weight:bold">사용날짜</td>
		
	</tr>
	
<c:choose>
<c:when test="${count == 0 }">
<tr>
	<td colspan=5 align="center">보유한 쿠폰이 없습니다.</td>
</tr>		
</c:when>
<c:otherwise>
<c:forEach var="list" items="${v}">
	<tr>
		<td align=center>${list.coupon_name }원 쿠폰</td>
		<fmt:parseDate value="${list.make_date}" pattern ="yyyyMMddhhmmss" var="date1"> </fmt:parseDate>
		<td align=center><fmt:formatDate value='${date1}' pattern='yyyy/MM/dd'></fmt:formatDate></td>
		
		<c:choose>
			<c:when test="${date > list.validate}">
				<td align="center">기간만료</td>
			</c:when>
			<c:otherwise>
				<fmt:parseDate value="${list.validate}" pattern ="yyyyMMddhhmmss" var="date2"> </fmt:parseDate>
				<td align=center>~ <fmt:formatDate value='${date2}' pattern='yyyy/MM/dd'></fmt:formatDate> 까지</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${list.coupon_use == 0 and date < list.validate}">
				<td align=center>미사용</td>
			</c:when>
			<c:when test="${list.coupon_use ==0 and date > list.validate}">
				<td align="center">사용불가</td>
			</c:when>
			<c:otherwise>
				<td align=center>사용</td>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${list.use_date == null and date < list.validate}">
				<td align=center>미사용</td>
			</c:when>
			<c:when test="${list.coupon_use ==0 and date > list.validate}">
				<td align="center">사용불가</td>
			</c:when>
			<c:otherwise>
				<td align=center>사용</td>
			</c:otherwise>
		</c:choose>
		
	</tr>
</c:forEach>
</c:otherwise>
</c:choose>
</table>
</body>
</html>
<%@ include file="/include/foot.jsp"%>