<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/head.jsp"%>

<form action="/trade/commentUpdate.do?uid=${uid}" method="post">
<input type="hidden" name="id" value="<%=session_id%>">
<input type="hidden" name="cuid" value="${comment.uid}">
<input type="hidden" name="uid" value="${uid}">
<input type="hidden" name="date">
<table width=80% align=center border=1 cellspacing=0>
	<tr>
		<td width="10%" align=center>${comment.replyid}</td>
		<td><input name="comment" maxlength=100 placeholder="(최대 100자까지)" value="${comment.replycomment}" style="width:100%; height:40px;"></td>
		<td width="10%" align=center><input type="submit" value="댓글수정" style="width:100%; height:40px" onsubmit="return false;"></td>
	</tr>
</table>
</form>

<%@include file="/include/foot.jsp"%>