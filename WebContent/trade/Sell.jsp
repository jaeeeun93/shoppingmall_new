<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/include/head.jsp"%>
<div style="height:150px;"></div>

<style>
div.hline{
	display:inline-block;
	background-color:#4285f4;
	padding : 0 800 0 800;
}
div.boardList{
	display:inline-block;
}

div.search{
	padding : 10 0 0 880;
	display:inline-block;
}

div.write{
	display:relative;

	background-color:#4285f4;
	
}
</style>

<div class="hline">
			total : ${count} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="/trade/List.do">최신순</a>
			<a href="/trade/hignRef.do">조회수</a>
			<a href="/trade/Sell.do">판매글</a>
			<a href="/trade/Buy.do">구매글</a>
</div>
<div class="boardList">
<c:if test="${count == 0}">
<table width=90% align=center height="900">
	<tr>
		<td align=center>게시글이 없습니다.</td>
	</tr>
</table>
</c:if>
<c:if test="${count != 0}">
<table width=90% align=center height="900">
	<tr>
	<c:set var="number" value="0"/>
	<c:forEach var="trade" items="${v}">
		<c:if test="${number%3==0}"><tr></c:if>  
		<td width=30% height=100% valign=top>
			<table cellspacing=0 border=1 width=100%>
				<tr>
					<td align=center rowspan=6 width="300" height="300"
					onclick="location.href='/trade/View.do?uid=${trade.uid}';" style="cursor: pointer">
						<c:choose>
							<c:when test="${trade.file_s == '' && trade.deal ne '3'}">
								<img src="/upload/noimg.jpg">
							 </c:when>
							<c:when test="${trade.deal eq '3'}">
								<img src="/upload/dealComp.jpg">
							 </c:when>
							<c:otherwise>
								<img src="/upload/${trade.file_s}">
							</c:otherwise>
						</c:choose>
					</td>
					<c:choose>
						<c:when test="${trade.deal eq '1'}">
							<td align=center style="font-weight:bold; font-size:18px; color:red;">[판매]</td>
						</c:when>
						<c:when test="${trade.deal eq '2'}">
							<td align=center style="font-weight:bold; font-size:18px; color:blue;">[구매]</td>
						</c:when>
						<c:otherwise>
							<td align=center style="font-weight:bold; font-size:18px;">[거래완료]</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td align=center>${trade.subject}</td>
				</tr>
				<tr>
				<c:choose>
					<c:when test="${trade.deal eq '3'}">
						<td align=center><strike>
							<fmt:formatNumber value="${trade.price}" type="currency"/>
						</td>
					</c:when>
					<c:otherwise>
						<td align=center>
							<fmt:formatNumber value="${trade.price}" type="currency"/>
						</td>
					</c:otherwise>
				</c:choose>
				</tr>
				<tr>
					<td align=center>작성자 : ${trade.id}</td>
				</tr>
				<tr>
					<td align=center>${trade.signdate}</td>
				</tr>
				<tr>
					<td align=center>👁(${trade.ref}) 💬(${trade.commentcnt}) ❤️(${trade.likecnt})</td>
				</tr>
			</table>
		</td>
		<c:if test="${number%3==2}"></tr></c:if>  
		<c:set var="number" value="${number+1}"/>
		</c:forEach>
	</tr>
</table>
</c:if>
<br>
<table width=90% align=center>
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
		<a href="/trade/Sell.do?pageNum=${startPage - pageBlock }">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="/trade/Sell.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 링크 -->
	<c:if test="${endPage < pageCount}">
		<a href="/trade/Sell.do?pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>
</div>
<div class="write">
<table width=90% align=center>
	<tr>
		<td align=left><a href="/trade/Sell.do">새로고침</a></td>
		<td align=right>
		<%if(session_level != null){ %>
			<a href="/trade/tradeWrite.jsp">글쓰기</a>
		<%}else{%>
			
		<%} %>
		</td>
	</tr>
</table>
</div>
<div class="search">
<form action="/trade/Sell.do" method="post">
<table align=center>
  <tr>
    <td>
   		<select name="field">
    		<option value="subject">제목</option>
    		<option value="id">작성자</option>
    	</select>&emsp;
		<input type="text" name="search">&emsp;
   		<input type="submit" value="검색">
    </td>
  </tr>
</table>
</form>
</div>
<%@include file="/include/foot.jsp"%>