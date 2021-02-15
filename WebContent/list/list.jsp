<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/head.jsp"%>

<style>
div.write{
	display:relative;

	background-color:#4285f4;
	
}
</style>

<div style="height:150px;"></div>
<table width=80% align=center>
<%-- ====================level=======<%=session_level %><br>
====================id=======<%=session_id %> --%>
	<tr>
		<td>total: ${count }</td><!-- 총게시글수 -->
	</tr>
</table>
<table width=80% border=1 align=center>
	<tr>
		<td>No.</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td colspan=2>조회</td>
	</tr>
	<c:set var="number"  value="${number }"/>
	<!-- dao allcount에서 return값을 servlet에서 number에 담아서 씀! -->

	<c:forEach var="list" items="${v }"><!-- list변수에 v를 담아서 v에 담은것들을 씀-->
	<!-- for 문 처럼 사용함!desc 를 이용해서 uid값과 number값이 같아지게 만들고 5일때 5uid글 출력 -->
	<tr>
		<td>${number }</td>
		<td>
		<!-- 2020.12.03추가 -->
		<c:if test="${list.file_s != ''}"><!-- do로 보내는 이유는 값을 같이 가져가서 jsp파일에서 보여주려고 -->
			<a href = "/list/View.do?uid=${list.uid }" >
			<img src="/upload/${list.file_s}">
			</a>
		</c:if>
		<a href = "/list/View.do?uid=${list.uid }" ><span style="color:#4285f4;">${list.subject }</span></a>
		</td>
		<td>${list.id }</td>
		<td>${list.signdate }</td>
		<td>${list.ref }</td>
	</tr>
	<c:set var="number" value="${number-1 }"/>
	<!-- 만약5개 들이 있다면-1시켜서 uid=4가 나오게 -->
	</c:forEach>
</table>
<br>
<div class="write">
<table align=center>
	<tr>
		<td align=center>
<!-- 페이징 시작 -->
<c:if test="${count  > 0}"><!-- 전체게시글수 -->
	<!-- pagesize 한페이지에 보여질 게시글 수 -->
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" /><!-- 0이아닐때 1 -->
	<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true"/><!-- []만들어주는 -->
		
		<!-- 2개의 변수 초기화 -->
		<!-- 시작페이지 1 -->
		<c:set var="startPage" value="${1 }"/>
		<!-- 3페이지씩 보여주겠다 [1][2][3]-->
		<c:set var="pageBlock" value="${3 }"/>
		
		<!-- 다음페이지 블럭이 존재할 경우 startPage 값 변경 부분-->
		<!-- pagenum이 4일 경우 -->
		<c:if test = "${pageNum > pageBlock}">
			<!-- 결과 값을 정수형으로 리턴 받아야 하기 때문에 fmt 처리 -->
			<!-- 4/3 - (4%3 = 1이라서 0) 4/3은 1 이니까 result=1-->
			<fmt:parseNumber var="result" value="${pageNum / pageBlock - (pageNum % pageBlock == 0 ? 1 : 0) }" integerOnly="true"/>
			<!-- 1*3 +1 = 4니까 4페이지로 넘어감 -->
			<c:set var="startPage" value="${result * pageBlock + 1 }"/>
		</c:if>
		
			<!-- endPage 설정 -->
		<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		
		<!-- 이전 링크 -->
		<!-- startpage가 4페이지 일때 3인pageblock보다 크니까 if문 실행-->
		<c:if test="${startPage > pageBlock }">
		<!-- 4-3=1이니까 1페이지로 -->
			<a href="/list/List.do?pageNum=${startPage - pageBlock }">[이전]</a>
		</c:if>
		
		<!-- 페이지 출력 -->
		<!--i:1 i<3 i++ 대략 이런 구문임-->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<!-- servlet에 있는 pagenum 구문 이용해서 -->
			<a href="/list/List.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		
		<!-- 다음 링크 -->
		<c:if test="${endPage < pageCount}">
		<!-- 1+3=4니까 -->
		<a href="/list/List.do?pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>
</div>
<!-- 페이징끝 -->
<table width= 80% align=center>
	<tr>
		<td align=left><a href="/list/List.do"><span style="color:#4285f4;">새로고침</span></a></td>
		<td align=right>
		<!-- 2020.11.27 수정 로그아웃 상태에서 안들어가져서 수정함-->
			<%if(session_level !=null && (session_level.equals("1") || session_level.equals("10"))){%>
				<a href = "/list/write.jsp"><span style="color:#4285f4;">글쓰기</span></a>
			<%}%>
		</td>
	</tr>
</table>
<form action="/list/List.do" method="post">
<table align=center>
	<tr>
		<td>
			<select name="field">
				<option value="id">아이디</option>
				<option value="subject">제목</option>
				<option value="signdate">작성일</option>
			</select>
			<input type="text" name="search">
			<input type="submit" value="검색">
		</td>
	</tr>
</table>
</form>
<%@include file="/include/foot.jsp"%>
