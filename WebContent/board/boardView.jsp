<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/include/head.jsp"%>

<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
<div style="height:150px;"></div>
<table width=80% align=center border=1>
	<tr align=center>
		<td>제목</td>
		<td>${view.subject}</td>
	</tr>
	<tr align=center>
		<td>작성자</td>
		<td>${view.id}</td>
	</tr>
	<tr align=center>
		<td>작성일</td>
		<td>${view.signdate}</td>
	</tr>
	<tr align=center>
		<td>조회수</td>
		<td>${view.ref}</td>
	</tr>
	<tr align=center>
		<td>내용</td>
	<!-- board테이블의 file칼럼의 default는 null이 아니라 '' 이라서 비교구문도 ''을 쓴다. -->
		<c:choose>
			<c:when test="${view.file eq ''}"> 
				<td>
					${fn:replace(view.comment,cn,br)}<br>
					파일이없습니다.
				</td>
			</c:when>
			<c:otherwise>
				<td>
					${fn:replace(view.comment,cn,br)}<br>
					<img src="/upload/${view.file}">
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr align=center>
		<td>파일다운로드</td>
		<td><a href="/upload/${view.file}" download><span style="color:#4285f4;">${view.file}</span></a></td>
	</tr>
</table>
<br>
<table width=80% align=center border=1>
	<tr>
		<td><a href="/board/boardList.do"><span style="color:#4285f4;">목록으로</span></a></td>
		<td align=right>
			<!--관리자만 삭제 및 수정가능, 다른게시판에서 사용시 세션아이디equals()로 작성자와 관리자만 삭제설정 -->
			<%if(session_level != null && session_level.equals("10")){ %>
				<input type="button" value="수정" onclick="location.href='/board/modify.do?uid=${view.uid}'">
			<%} %>	
			&nbsp;
			<%if(session_level != null && session_level.equals("10")){ %>
				<input type="button" value="삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/board/delete.do?uid=${view.uid}'}">
			<%} %>
		</td>
	</tr>
</table>

<%@include file="/include/foot.jsp"%>