<!-- //2020.11.27 수정! -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/head.jsp"%>
<div style="height:150px;"></div>
<table width=100% border=1>
	<tr>
		<td>제목</td>
		<td>${view.subject }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${view.id }</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${view.ref }</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${view.signdate }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${view.comment }</td>
	</tr>
	<!-- 2020.12.02추가! -->
	<tr>
	<!-- board테이블의 file칼럼의 default는 null이 아니라 '' 이라서 비교구문도 ''을 쓴다. -->
		<c:choose>
			<c:when test="${view.file eq ''}"> 
				<td>파일이미지</td>
				<td>파일없음</td>
			</c:when>
			<c:otherwise>
				<td>파일이미지</td>
				<td><img src="/upload/${view.file}"></td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td>파일다운로드</td>
		<td><a href="/upload/${view.file}" download>${view.file}</a></td>
	</tr>
</table> 
<table>
	<tr>
		<td><a href="/list/List.do"><span style="color:#4285f4;">목록으로</span></a></td>
		<td>
			<c:set var="session_level" value="<%=session_level %>" /> <!-- head에 이미 세션값있으니까 불러와서 변수에 담아쓴다 -->
			<c:set var="session_id" value="<%=session_id %>" />
			<c:choose>
				<c:when test="${session_level != null && session_level == '10' }"><!-- if -->
					<input type="button" value="수정10"  onclick="location.href='/list/ListModify.do?uid=${view.uid }'">
					<input type="button" value="삭제" onclick="if(confirm('정말로 삭제 하시겠습니까?')){location.href='/list/Listdelete.do?uid=${view.uid}'}'">
				</c:when>
				<c:when test="${session_id != null && session_id == view.id }"><!-- else if -->
					<input type="button" value="수정2"  onclick="location.href='/list/ListModify.do?uid=${view.uid }'">
					<input type="button" value="삭제" onclick="if(confirm('정말로 삭제 하시겠습니까?')){location.href='/list/Listdelete.do?uid=${view.uid}'}'">
				</c:when>
				<c:otherwise><!-- else-->
					<input type="button" value="수정"  onclick="alert('작성권한이 없습니다.');">
					<input type="button" value="삭제"  onclick="alert('삭제권한이 없습니다.');">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<%@include file="/include/foot.jsp"%>
