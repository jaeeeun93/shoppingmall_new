<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<div style="height:150px;"></div>
<form action="/review/reviewWrite.do" method="post" enctype="multipart/form-data">
<table width=100% height=700 border=1 cellspacing=0>
	<c:forEach var="list" items="${p}" begin="0" end="0">
		<tr>
			<th colspan=2>구매후기 쓰기</th>
		<tr>	
			<td align=center><a href="/admin/item/info.do?code=${code}"><input type="image" name="" src="/upload/${list.cartFile_s }" readonly></a></td>
			<td align=center>
				<input name="itemName" value="${list.itemName}" readonly><br>
				<input name="itemPrice" value="${list.itemPrice}" readonly><br>
			</td>
			<input type="hidden" name="itemCode" value="${list.itemCode }">
		</tr>
		<tr>
			<td>별점</td>
			<td>
				<input type="radio" id="star5" name="star" value="5">★★★★★<br>
				<input type="radio" id="star4" name="star" value="4">★★★★<br>
				<input type="radio" id="star3" name="star" value="3">★★★<br>
				<input type="radio" id="star2" name="star" value="2">★★<br>
				<input type="radio" id="star1" name="star" value="1">★
			</td>
		</tr>
		<tr>
			<td>구매후기</td>
			<td><input name="review"></td>
		</tr>
		<tr>
			<td>사진첨부</td>
			<td><input type="file" name="itemFile"></td>
		</tr>
		<tr>
			<td>한줄요약</td>
			<td><input name="summary"></td>
		</tr>
		<tr>
			<td colspan=2 align="center"><input type="submit" value="등록하기"></td>
		</tr>
		</c:forEach>
</table>
</form>
</body>
</html>