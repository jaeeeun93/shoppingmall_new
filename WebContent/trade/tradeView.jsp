<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/include/head.jsp"%>

<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
<div style="height:150px;"></div>
<table width=80% align=center border=1>
	<tr>
		<td align=center>판매, 구매</td>
		<c:choose>
			<c:when test="${view.deal eq '1'}">
				<td align=center style="font-weight:bold; font-size:18px; color:red;">[판매]</td>
			</c:when>
			<c:when test="${view.deal eq '2'}">
				<td align=center style="font-weight:bold; font-size:18px; color:blue;">[구매]</td>
			</c:when>
			<c:otherwise>
				<td align=center style="font-weight:bold; font-size:18px;">[거래완료]</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td align=center>제목</td>
		<td align=center>${view.subject}</td>
	</tr>
	<tr>
		<td align=center>작성자</td>
		<td align=center>${view.id}</td>
	</tr>
	<tr>
		<td align=center>작성일</td>
		<td align=center>${view.signdate}</td>
	</tr>
	<tr>
		<td align=center>조회수</td>
		<td align=center>${view.ref}</td>
	</tr>
	<tr>
		<td align=center>가격</td>
		<td align=center>
			<c:set var="a" value="${view.price}"/>
			<fmt:formatNumber value="${a}" type="currency"/>
		</td>
	</tr>
	<tr>
		<td align=center>쪽지</td>
		<td align=center>
			<a href="/message/messageTrade.jsp?id=${view.id}&uid=${view.uid}"><span style="color:#4285f4;">쪽지를 보내려면 클릭하세요</span></a>
		</td>
	</tr>
	<tr>
		<td align=center>내용</td>
		<td align=center>
		<br><c:if test="${view.file ne ''}"><img src="/upload/${view.file}"></c:if><br>
		<c:if test="${view.file2 ne ''}"><img src="/upload/${view.file2}"></c:if><br>
		<c:if test="${view.file3 ne ''}"><img src="/upload/${view.file3}"></c:if><br>
		<c:if test="${view.file4 ne ''}"><img src="/upload/${view.file4}"></c:if><br>
		<br>
		<br>${fn:replace(view.comment,cn,br)}<br><br>
		</td>
	</tr>
</table>
<br>
<!-- 댓글 작성 -->
<form action="/trade/commentIn.do" method="post">
<input type="hidden" name="id" value="<%=session_id%>">
<input type="hidden" name="uid" value="${view.uid}">
<input type="hidden" name="date">
<table width=80% align=center border=1 cellspacing=0>
	<tr>
		<td width="10%" align=center><%=session_id %></td>
		<td><input name="comment" maxlength=100 placeholder="(최대 100자까지)" style="width:100%; height:40px;"></td>
		<td width="10%" align=center><input type="submit" value="댓글작성" style="width:100%; height:40px" onsubmit="return false;"></td>
	</tr>
</table>
</form>
<!-- 작성된 댓글 목록 -->
<c:choose>
<c:when test="${view.commentcnt == 0}">
	<br><p align=center>작성된 댓글이 없습니다.</p>
</c:when>
<c:otherwise>
<c:if test="${comment.uid != 0}">
<c:forEach var="comment" items="${v}">
<table width=80% align=center border=1>
	<tr>
		<c:if test="${comment.commenttype == 1}">
		<td width=3% align=center>Re.</td>
		</c:if>
		<td width="10%" align=center>${comment.replyid}</td>
		<td > &nbsp; ${comment.replycomment}</td>
		<td width="7%" align=center>
			<c:set var="session_level" value="<%=session_level %>"/>
			<c:set var="session_id" value="<%=session_id %>"/>
			<c:choose>
				<c:when test="${comment.replyid eq session_id}">
					<input type="button" value="수정" onclick="location.href='/trade/commentModify.do?commentuid=${comment.uid}&uid=${view.uid}'">
					<input type="button" value="삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/trade/commentDel.do?uid=${view.uid}&Commentuid=${comment.uid}'}">
				</c:when>
				<c:when test="${session_level eq '10'}">
					<input type="button" value="수정" onclick="location.href='/trade/commentModify.do?commentuid=${comment.uid}&uid=${view.uid}'">
					<input type="button" value="삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/trade/commentDel.do?uid=${view.uid}&Commentuid=${comment.uid}'}">
				</c:when>
				<c:otherwise/>
			</c:choose>
		</td>
		<td width="10%" align=center>${comment.replydate}</td>
	</tr>
	<tr>
	<!-- 대댓글 작성하기 -->
	<c:if test="${comment.commenttype == 0}">
	<form action="/trade/reComment.do?uid=${view.uid}&fid=${comment.fid}" method="post">
	<input type="hidden" name="id" value="<%=session_id%>">
	<input type="hidden" name="uid" value="${view.uid}">
	<input type="hidden" name="date">
	<table width=80% align=center border=1>
		<tr>
			<td></td>
			<td width="10%" align=center><%=session_id %></td>
			<td><input name="comment" maxlength=100 placeholder="(최대 100자까지)" style="width:100%; height:25px;"></td>
			<td width="10%" align=center><input type="submit" value="답변" style="width:100%; height:25px" onsubmit="return false;"></td>
		</tr>
	</table>
	</form>
	</c:if>
	</tr>
</table>
</c:forEach>
</c:if>
</c:otherwise>
</c:choose>
<br>
<!-- 게시글 수정,삭제 -->
<table width=80% align=center border=1>
	<tr>
		<td><a href="/trade/List.do">목록으로</a></td>
		<td align=right>
			<c:set var="session_level" value="<%=session_level %>"/>
			<c:set var="session_id" value="<%=session_id %>"/>
			<c:choose>
				<c:when test="${view.id eq session_id}">
					<input type="button" value="수정" onclick="location.href='/trade/modify.do?uid=${view.uid}'">
					<input type="button" value="삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/trade/delete.do?uid=${view.uid}'}">
				</c:when>
				<c:when test="${session_level eq '10'}">
					<input type="button" value="수정" onclick="location.href='/trade/modify.do?uid=${view.uid}'">
					<input type="button" value="삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/trade/delete.do?uid=${view.uid}'}">
				</c:when>
				<c:otherwise/>
			</c:choose>
		</td>
	</tr>
</table>

<div style="height:135px;"></div>
<%@include file="/include/foot.jsp"%>