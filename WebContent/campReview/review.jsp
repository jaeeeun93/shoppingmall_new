<!-- //2020.12.01 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- frameset은 영역나누는 거고 frame에 넣고싶은거 넣음! -->
<!--크게 가로로 3등분 해더 컨텐츠 넣을 부분 푸터-->
<frameset rows=165,*,60 border=1>
	<frame src=/include/head.jsp> 
	<!-- 컨텐츠 부분 영역 쪼개기 -->
	<frameset cols=800,*>
		<frame name="left" src="/campReview/ReviewList.do">
		<frame name="right">
	</frameset>
	<!-- 푸터 -->
		<frame src="/include/foot.jsp">
</frameset>
</html>