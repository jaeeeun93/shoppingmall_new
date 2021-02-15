<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/admin/include/head.jsp"%>

<script>
function all_checked(sw) {
    var f = document.msg;

    for (var i=0; i<f.length; i++) {
        if (f.elements[i].name == "chk[]")
            f.elements[i].checked = sw;
    }
}

function send(f) {
    var chk_count = 0;
	var chk_str = "";
	
    for (var i=0; i<f.length; i++) {
        if (f.elements[i].name == "chk[]" && f.elements[i].checked == true){
            chk_count++;
        	chk_str += f.elements[i].value+"-";
        }
    }

    if (!chk_count) {
        alert("쪽지를 하나 이상 선택하세요.");
        return false;
    }

	if (!confirm("선택한 쪽지를 삭제하시겠습니까?"))
	    return false;
	
	f.removeAttribute("target");
	f.action = "/message/messageDelete.do?flag=1&str="+chk_str;

    return true;
}
</script>

</head>
<body>
<form name="msg" action="" method="post" onsubmit="return send(this);">

<table width=100% height=300 border=1>
<c:choose>
<c:when test="${count == 0 }">
	<tr>
		<td align="center" style="font-weight:bold"><input type="checkbox" onclick="if (this.checked) all_checked(true); else all_checked(false);" checked></td>
		<td align=center>보낸사람</td>
		<td align=center>내용</td>
		<td align=center>받은날짜</td>
		<td align=center>확인</td>
	</tr>
	<tr>
		<td colspan=5 align=center>받은 쪽지가 없습니다.</td>
	</tr>
</c:when>
<c:otherwise>
	<tr>
		<td align="center" style="font-weight:bold"><input type="checkbox" onclick="if (this.checked) all_checked(true); else all_checked(false);" checked></td>
		<td>보낸사람</td>
		<td>내용</td>
		<td>받은날짜</td>
		<td>확인</td>
	</tr>
	
<c:forEach var="list" items="${msg}">
<fmt:parseDate value="${list.send_date}" pattern ="yyyyMMddhhmmss" var="date"> </fmt:parseDate>
	<tr>
		<td align=center><input type="checkbox" name="chk[]" value="${list.uid }" checked></td>
		<td><input name="" value="${list.send_id}"></td>
		<td><a href="/admin/Inquire/messageDetail.do?uid=${list.uid}">${list.content}</a></td>
		<td><input name="" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd hh:mm'></fmt:formatDate>" readonly></td>
		<td>
			<c:choose>
			<c:when test="${list.msg_read == 0}" >
				안읽음
			</c:when>
			<c:otherwise>
				읽음
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan=5 align=center><input type="submit" value="삭제"></td>
	</tr>
</c:otherwise>
</c:choose>
</table>
</form>

<%@include file="/admin/include/foot.jsp"%>