<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@include file="/include/head.jsp"%>
<div style="height:150px;"></div>

<style>
div.write{
	display:relative;

	background-color:#4285f4;
	
}
</style>
<table width=80% align=center>
	<tr>
		<td>total : ${count}</td>
	</tr>
</table>
<table width=80% border=1 cellspacing=0 align=center>
	<tr>
		<td align="center" style="font-weight:bold">No.</td>
		<td align="center" style="font-weight:bold">제목</td>
		<td align="center" style="font-weight:bold">작성자</td>
		<td align="center" style="font-weight:bold">작성일</td>
		<td align="center" style="font-weight:bold">조회수</td>
	</tr>
	<c:set var="number" value="${number}"/>
	<c:if test="${count eq 0}"><td align=center colspan=5>작성한 게시글이 없습니다.</td></c:if>
	<c:if test="${count ne 0}">
	<c:forEach var="board" items="${v}">
	<tr align=center>
		<td>${number}</td>
		<td width=50%><a href="/board/View.do?uid=${board.uid}"><span style="color:#4285f4;">${board.subject}</span></a></td>
		<td>${board.id}</td>
		<td>${board.signdate}</td>
		<td>${board.ref}</td>
	</tr>
	<c:set var="number" value="${number-1}"/>
	</c:forEach>
	</c:if>
</table>
<br>
<div class="write">
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
</div>
<table width=80% align=center>
	<tr>
		<td align=left><a href="/board/boardList.do"><span style="color:#4285f4;">새로고침</span></a></td>
		<td align=right>
		<%if(session_level != null && session_level.equals("10")){ %>
			<a href="/board/write.jsp"><span style="color:#4285f4;">글쓰기</span></a>
		<%}else{%>
		
		<%} %>
		</td>
	</tr>
</table>
<form action="/board/boardList.do" method="post">
<table align=center>
  <tr>
    <td>
   		<select name="field">
    		<option value="subject">제목</option>
    		<option value="signdate">작성일</option>
    	</select>&emsp;
		<input type="text" name="search">&emsp;
   		<input type="submit" value="검색">
    </td>
  </tr>
</table>
</form>
<%@include file="/include/foot.jsp"%>