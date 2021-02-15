<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function validate(){
		
		if (msg.content.value == "") {
			alert("내용을 입력해주세요");
			msg.content.focus();
			return false;
		}
		alert("쪽지 전송 완료");
	}
</script>

</head>
<body>
<%
	String id = request.getParameter("id");
	String uid = request.getParameter("uid");
%>
<div style="height:150px;"></div>
<form name="msg" action="/message/messageWrite.do" method="post" onsubmit="return validate();">
<table border=1 width=700 height=500 align=center>
	<tr>
		<td>받는사람</td>
		<td><input name="recv_id" value="<%=id%>" readonly></td>
	</tr>
	<tr>
		<td colspan=2><input name="content"></td>
	</tr>
	<tr>
		<td align=center><button type="button" onclick="location.href='/trade/View.do?uid=<%=uid%>';">뒤로가기</button></td>
		<td align=center><input type="submit" value="보내기"></td>
	</tr>
</table>
</form>
</body>
</html>