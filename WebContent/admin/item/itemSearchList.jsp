<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URI"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<style>
div.pagenum{
	display:relative;

	left : 47%;
	background-color:#4285f4;
	
}
</style>
<div style="height:150px;"></div>
total : ${count}
<table width=100% height=600 border=1 cellspacing=0>
	<tr>
		<td colspan=7 align=center style="font-weight:bold; background-color:lightgray;">낮은가격순</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold">No.</td>
		<td align="center" style="font-weight:bold">상품명</td>
		<td align="center" style="font-weight:bold">상품썸네일</td>
		<td align="center" style="font-weight:bold">상품가격</td>
		<td align="center" style="font-weight:bold">적립포인트</td>
		<td>찜</td>
	</tr>
	<c:set var="number" value="${number}"/>
	<c:forEach var="list" items="${v}" varStatus="status">
	
	<tr>
		<td>${number}</td>
		<td>${list.itemName}</td>
		<td>
			<a href="/admin/item/info.do?code=${list.itemCode}&name=${list.itemName}">
			<img src="/upload/${list.itemFile_s}" width=100 height=100></a><br>
			<c:choose>
					<c:when test="${list.starPoint == 0}">
 						<span style="color:orange">☆☆☆☆☆(0개의 리뷰)</span>
 					</c:when>
 					<c:when test="${list.starPoint / list.pnum eq 5}">
 						<span style="color:orange">★★★★★(${list.pnum }개의 리뷰)</span>
 					</c:when>
 					<c:when test="${list.starPoint / list.pnum >=4}">
 						<span style="color:orange">★★★★☆(${list.pnum}개의 리뷰)</span>
 					</c:when>
					<c:when test="${list.starPoint / list.pnum >=3}">
 						<span style="color:orange">★★★☆☆(${list.pnum }개의 리뷰)</span>
 					</c:when>
 					<c:when test="${list.starPoint / list.pnum >=2}">
 						<span style="color:orange">★★☆☆☆(${list.pnum }개의 리뷰)</span>
 					</c:when>
 					<c:when test="${list.starPoint / list.pnum >=1}">
 						<span style="color:orange">★☆☆☆☆(${list.pnum }개의 리뷰)</span>
 					</c:when>
 					<c:otherwise>
 						<span style="color:orange">☆☆☆☆☆(0개의 리뷰)</span>
 					</c:otherwise>
				</c:choose>
		</td>
		<td>
			<c:if test="${list.sold eq '판매'}">
				<fmt:formatNumber type='number' maxFractionDigits='3' value='${list.itemPrice}'/>원
			</c:if>
			<c:if test="${list.sold eq '품절'}">
				<p style="color:red;">품절</p>
			</c:if>
		</td>
		<td>${list.itemPoint}포인트</td>
		<input type="hidden" value="${list.stock}">
		<td>
			<c:forEach var="list2" items="${v2}">
				<c:if test="${list.itemUid == list2.itemUid }">
					<c:choose>
						<c:when test="${list2.jjim == 0}">
							<button onclick="abc('${list.itemUid}','<%=session_id %>')">🤍</button>
						</c:when>
						<c:when test="${list2.jjim == 1}">
							<button onclick="n('${list.itemUid}','<%=session_id %>')">❤️</button>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</c:if>
				
			</c:forEach>
		</td>
	</tr>
	<c:set var="number" value="${number-1}"/>
	</c:forEach>
</table>
<br>
<script>

function abc(itemUid, se_id){
	var delConfirm = confirm('해당 상품을 찜 하시겠습니까?');
	if (delConfirm) {
	      alert('해당 상품을 찜 목록에 추가');
	      location.href="/jjim.do?itemUid="+itemUid+"&id="+se_id+"&jjim=1&search=1";
	}
	else {
		alert('취소');
	}
}

function n(itemUid, se_id){
	var delConfirm = confirm('해당 상품을 찜 목록에서 제거하시겠습니까?');
	   if (delConfirm) {
	      alert('찜 목록에서 상품이 제거됩니다.');
	      location.href="/jjimCancel.do?itemUid="+itemUid+"&id="+se_id+"&jjim=0&flag=0&search=0";
	   }
	   else {
	      alert('취소');
	   }
}
</script>
<div class="pagenum">
<table width=100%>
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
		<a href="/admin/item/list.do?pageNum=${startPage - pageBlock }">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="/admin/item/list.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 링크 -->
	<c:if test="${endPage < pageCount}">
		<a href="/admin/item/list.do?pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>
</div>
<form action="/admin/item/list.do" method="post">
<table align=center>
  <tr>
    <td>
    	<select name="field">
    		<option value="itemName">상품명</option>
    	</select>&emsp;
    <input type="text" name="search">&emsp;
    <input type="submit" value="검색">
  </tr>
</table>
</form>

<%@include file="/include/foot.jsp"%>