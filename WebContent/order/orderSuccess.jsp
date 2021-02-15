<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URI"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<script>
function noEvent() { 
	if (event.keyCode == 116 || event.keyCode == 9) { 
	alert('No!'); 
	return false; 
	} 
	else if (event.ctrlKey && (event.keyCode = 78 || event.keyCode == 82)) { 
	return false; 
	} 
	} 
	document.onkeydown = noEvent; 
</script>

</head>
<body>
<h3>결제 성공(주문이 완료되었습니다.)</h3>

<button type="button" onclick="location.href='/admin/item/list.do'">계속 쇼핑하기</button>
</body>
</html>