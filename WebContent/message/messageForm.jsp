<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>

	function validate(){
		
		if (msg.recv_id.value == "") {
			alert("아이디를 입력해 주세");
			msg.recv_id.focus();
			return false;
		}
		
		if (msg.content.value == "") {
			alert("내용을 입력해주세요");
			msg.content.focus();
			return false;
		}
	}
</script>
</head>
<body>
<div style="height:150px;"></div>
<form name="msg" action="/message/messageWrite.do" onsubmit="return validate(this);" method="post">
<table border=1 width=700 height=500 align=center>
	<tr>
		<td>받는사람</td>
		<td><input id="recv_id" name="recv_id"></td>
	</tr>
	<tr>
		<td colspan=2><input id="content" name="content"></td>
	</tr>
	<tr>
		<td colspan=2 align=center><input type="submit" value="보내기"></td>
	</tr>
</table>
</form>
</body>
</html>