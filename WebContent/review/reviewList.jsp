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
<table width=100% height=300 border=1 cellspacing=0>
<c:set var="test" value="<%=total2 %>"/>
<c:choose>
<c:when test="${test == 0 }">
<tr>
	<td align="center">구매후기 작성 가능한 구매 상품이 없습니다.</td>
</tr>		
</c:when>
<c:otherwise>
<th width=50%><a href="/review/reviewList.do?id=<%=session_id%>"><span style="color:#4285f4;">작성 가능 구매후기</span></a></th>
<th colspan=2 width=50%><a href="/review/wroteReview.do?id=<%=session_id%>"><span style="color:#4285f4;">내가 쓴 구매후기</span></a></th>
	<c:forEach var="list" items="${p}" varStatus="status">
	<c:set var="list2" value="${v[status.index]}"/>
	<fmt:parseDate value="${list.orderDate}" pattern ="yyyyMMddhhmmss" var="date"> </fmt:parseDate>
		<tr>
			<td colspan=3><input name="itemPrice" value="주문일 <fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly></td>
		<tr>	
			<td align=center><a href="/admin/item/info.do?code=${list.itemCode}"><input type="image" name="" src="/upload/${list.cartFile_s }" readonly></a></td>
			<td align=center>
				<input name="orderDate" value="${list.itemName}" readonly><br>
				<input name="itemPrice" value="${list.itemPrice}원" readonly><br>
			</td>
			<td align=center>
				<a href="/review/reviewForm.do?code=${list.itemCode}"><span style="color:#4285f4;">구매후기 쓰기<span style="color:#4285f4;"></span></a><br>
			</td>
		</tr>
	</c:forEach>
</c:otherwise>
</c:choose>
</table>
</form>
</body>
</html>