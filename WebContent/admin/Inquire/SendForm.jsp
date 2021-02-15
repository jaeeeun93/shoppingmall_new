<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/admin/include/head.jsp"%>

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
%>
<form name="msg" action="/message/messageWrite.do" method="post" onsubmit="return validate();">
<table border=1 width=700 height=500>
	<tr>
		<td>받는사람</td>
		<td><input name="recv_id" value="<%=id%>" readonly></td>
	</tr>
	<tr>
		<td colspan=2><input name="content"></td>
	</tr>
	<tr>
		<td align=center><button type="button" onclick="location.href='/admin/InquireReceive.do';">목록으로</button></td>
		<td align=center><input type="submit" value="보내기"></td>
	</tr>
</table>
</form>

<%@include file="/admin/include/foot.jsp"%>