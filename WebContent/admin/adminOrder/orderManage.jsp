<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/admin/include/head.jsp"%>

<table width=80% align=center>
	<tr>
		<td>total : ${count}</td>
	</tr>
</table>

<table width=90% align=center border=1 cellspacing=0>
	<tr align=center>
		<td>주문번호</td>
		<td>주문일</td>
		<td>아이디</td>
		<td>전화번호</td>
		<td>우편번호</td>
		<td colspan=3>상세주소</td>
		<td>수령인</td>
		<td>수령인 전화번호</td>
		<td>수령지</td>
		<td>총 금액</td>
		<td>배송여부</td>
		<td>배송관리</td>
	</tr>
	<c:forEach var="order" items="${v}">
	<tr align=center>
		<td>${order.orderId}</td>
		<td>${order.orderDate}</td>
		<td>${order.id}</td>
		<td>${order.phone}</td>
		<td>${order.zipcode}</td>
		<td>${order.zip1}</td>
		<td>${order.zip2}</td>
		<td>${order.zip3}</td>
		<td>${order.cname}</td>
		<td>${order.cphone}</td>
		<td>${order.pick}</td>
		<td><fmt:formatNumber value="${order.totalPrice}" type="currency"/></td>
		<td>${order.delivery}</td>
		<td width=9%>
			<c:if test="${order.delivery eq '결제완료'}">
				<input type="button" onclick="location.href='/admin/order/delivery.do?uid=${order.orderUid}&id=${order.orderId }'" value="배송중">
			</c:if>	
			<c:if test="${order.delivery ne '결제완료'}">
				<input type="button" onclick="location.href='/admin/order/deliveryStart.do?uid=${order.orderUid}&id=${order.orderId }'" value="배송중">
				<input type="button" onclick="location.href='/admin/order/deliveryOk.do?uid=${order.orderUid}&id=${order.orderId }'" value="배송완료">
				<input type="button" onclick="location.href='/admin/order/deliveryUndo.do?uid=${order.orderUid}&id=${order.orderId }'" value="배송취소">
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

<table width=80% align=center>
	<tr>
		<td align=center>
<!-- 페이징 처리 -->		
<c:if test="${count  > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" />
	<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true"/>
	
	<!-- 2개의 변수 초기화 -->
	<c:set var="startPage" value="${1 }"/>
	<c:set var="pageBlock" value="${3 }"/>
	
	<!-- 다음 페이지 블럭이 존재 할 경우 startPage 값 변경 부분 -->
	<c:if test="${pageNum > pageBlock }">
		<!-- 결과 값을 정수형으로 리턴 받아야 하기 때문에 fmt 처리 -->
		<fmt:parseNumber var="result" value="${pageNum / pageBlock - (pageNum % pageBlock == 0 ? 1 : 0) }" integerOnly="true"/>
		<c:set var="startPage" value="${result * pageBlock + 1 }"/>
	</c:if>
	
	<!-- endPage 설정 -->
	<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
	<c:if test="${endPage > pageCount }">
		<c:set var="endPage" value="${pageCount }"/>
	</c:if>
	
	<!-- 이전 링크 -->
	<c:if test="${startPage > pageBlock }">
		<a href="/board/boardList.do?pageNum=${startPage - pageBlock }">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="/board/boardList.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 링크 -->
	<c:if test="${endPage < pageCount}">
		<a href="/board/boardList.do?pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>

<%@ include file="/admin/include/foot.jsp" %>