<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${idOk == 'y'}">
		<script>
			//자식창에서 부모창으로 값을 공유할땐 opener를 사용한다.
			alert("${msg}");
			opener.document.getElementById("check").value = "y";
			opener.document.getElementById("check3").value = "y";
			window.close();
			//iput id가 check 인것을 찾아감!중복체크를 했으니 check value를 y로 변경
			/* opener.document.getElementByName("idDuplication").value = "idCheck"; */
			//==join.idDuplication.value해도 같음->name 값으로 value확인
		</script>
	</c:if>
	<c:if test="${idOk == 'n'}">
		<script>
			alert("${msg}");
			opener.document.getElementById("check").value = "y";
			window.close();
		</script>
	</c:if>
</body>
</html>