<!-- //2020.12.01 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@include file="/include/head.jsp"%> --%>
<%
String session_id = (String)session.getAttribute("id");
String session_name = (String)session.getAttribute("name");
String session_level = (String)session.getAttribute("level");
%>

<table width=80% align=center>
<%-- 
====================level=======<%=session_level %><br>
====================id=======<%=session_id %>
--%>
	<tr>
		<td>total: ${count }</td>
	</tr>
</table>
<table width=80% border=1 align=center>
	<tr>
		<td>No.</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회</td>
	</tr>
	<c:set var="number"  value="${number }"/>
	<!-- dao allcount에서 return값을 servlet에서 number에 담아서 씀! -->

	<c:forEach var="list" items="${v }">
	<!-- for 문 처럼 사용함! desc 를 이용해서 uid값과 number값이 같아지게 만들고 5일때 5uid글 출력 -->
	<tr>
		<!-- //총 게시물 수  -->
		<td>${number }</td>
		<!-- 2020.12 03추가 -->
		<td>
			<c:if test="${list.file_s != ''}">
				<a href = "/campReview/View.do?uid=${list.uid }" target="right">
				<img src="/upload/${list.file_s}">
				</a>
			</c:if>
		<a href = "/campReview/View.do?uid=${list.uid }" target="right">${list.subject }</a>
		</td>
		<!-- do로 보내는 이유는 값을 같이 가져가서 jsp파일에서 보여주려고 -->
		<td>${list.id }</td>
		<td>${list.signdate }</td>
		<td>${list.ref }</td>
	</tr>
	<c:set var="number" value="${number-1 }"/>
	<!-- -1시켜서 4 uid가 나오게 -->
	</c:forEach>
	
</table>
<br>
<table align=center>
	<tr>
		<td align=center>
<!-- 페이징 시작 -->
<c:if test="${count  > 0}"><!-- 전체게시판수 -->
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" /><!-- 0이아닐때 1 -->
	<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true"/>
		
		<!-- 2개의 변수 초기화 -->
		<c:set var="startPage" value="${1 }"/>
		<c:set var="pageBlock" value="${3 }"/>
		
		<!-- 다음페이지 블럭이 존재할 경우 startPage 값 변경 부분-->
		<c:if test = "${pageNum > pageBlock}">
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
		<a href="/campReview/ReviewList.do?pageNum=${startPage - pageBlock }">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="/campReview/ReviewList.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 링크 -->
	<c:if test="${endPage < pageCount}">
		<a href="/campReview/ReviewList.do?pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>
		</td>
	</tr>
</table>
<!-- 페이징끝 -->
<table>
	<tr>
		<td><a href="/campReview/ReviewList.do">새로고침</a></td>
		<td>
		<!-- 2020.11.27 수정 로그아웃 상태에서 안들어가져서 수정함-->
			<%if(session_level !=null && (session_level.equals("1") || session_level.equals("10"))){%>
				<a href = "/campReview/reviewWrite.jsp">글쓰기</a>
			<%}%>
		</td>
	</tr>
</table>
<form action="/campReview/ReviewList.do" method="post">
<table align=center>
  <tr>
    <td>
   		<select name="field">
    		<option value="id">아이디</option>
    		<option value="subject">제목</option>
    		<option value="signdate">작성일</option>
    	</select>&emsp;
		<input type="text" name="search">&emsp;
   		<input type="submit" value="검색">
    </td>
  </tr>
</table>
</form>
<%-- <%@include file="/include/foot.jsp"%> --%>
