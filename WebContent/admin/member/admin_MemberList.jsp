<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/admin/include/head.jsp"%>

total : ${count}
<table width=100% border=1 cellspacing=0>
	<tr>
		<td colspan=13 align=center style="font-weight:bold; background-color:lightgray;">회원목록</td>
	</tr>
	<tr>
		<td align="center" style="font-weight:bold">No.</td>
		<td align="center" style="font-weight:bold">아이디</td>
		<td align="center" style="font-weight:bold">이름</td>
		<td align="center" style="font-weight:bold">이메일</td>
		<td align="center" style="font-weight:bold">레벨</td>
		<td align="center" style="font-weight:bold">주소</td>
		<td align="center" style="font-weight:bold">전화번호</td>
		<td align="center" style="font-weight:bold">가입일자</td>
		<td align="center" style="font-weight:bold">포인트</td>
		<td align="center" style="font-weight:bold">쿠폰</td>
		<td align="center" style="font-weight:bold">탈퇴여부</td>
		<td align="center" style="font-weight:bold">회원상세정보</td>
	</tr>
	<c:set var="number" value="${number}"/>
	<c:forEach var="list" items="${v}">
	<tr>
		<td>${number}</td>
		<td>${list.id}</td>
		<td>${list.name}</td>
		<td>${list.email}</td>
		<td>${list.level}</td>
		<td>${list.zipcode} ${list.zip1} ${list.zip2} ${list.zip3}</td>
		<td>${list.phone}</td>
		<td>${list.joinDate}</td>
		<td>${list.point}</td>
		<td>${list.coupon}</td>
		<td>${list.userDel}</td>
		<td>
			<a href="/admin/member/modify.do?id=${list.id}">[수정]</a>
			<a href="/admin/member/delete.do?id=${list.id}">[삭제]</a>
		</td>
	</tr>
	<c:set var="number" value="${number-1}"/>
	</c:forEach>
</table>
<br>
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
		<a href="/admin/member/list.do?pageNum=${startPage - pageBlock }">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="/admin/member/list.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 링크 -->
	<c:if test="${endPage < pageCount}">
		<a href="/admin/member/list.do?pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>

<form action="/admin/member/search.do" method="post">
<table align=center>
  <tr>
    <td>
   		<select name="field">
    		<option value="id">아이디</option>
    		<option value="name">이름</option>
    		<option value="level">레벨</option>
    	</select>&emsp;
		<input type="text" name="search">&emsp;
   		<input type="submit" value="검색">
    </td>
  </tr>
</table>
</form>

<%@include file="/admin/include/foot.jsp"%>