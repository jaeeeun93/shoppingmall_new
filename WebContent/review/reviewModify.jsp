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
<form action="/review/reviewUpdate.do?minus=${starPoint }" method="post" enctype="multipart/form-data">

<table width=100% height=700 border=1 cellspacing=0>
		<tr>
			<th colspan=2>구매후기 수정</th>
		<tr>	
			<td align=center>
			<c:choose>
				<c:when test="${review.itemFile_s == ''}">
						<img src="/upload/noimg.jpg">
				</c:when>
			<c:otherwise>			
			<a href="/admin/item/info.do?code=${code}"><input type="image" name="" src="/upload/${review.itemFile_s }" onerror="this.src='/upload/no2.jpg'" readonly></a></td>
			</c:otherwise>
			</c:choose>
			<td align=center>
				<input name="itemName" value="${review.itemName}" readonly><br>
				<input type="hidden" name="uid" value="${reviewUid }">
			</td>
			<input type="hidden" name="itemCode" value="${review.itemCode }">
		</tr>
		<tr>
			<td>별점</td>
			<td>
			<c:choose>
				<c:when test="${starPoint == 5}">
					<input type="radio" id="star5" name="star" value="5" checked>★★★★★<br>
					<input type="radio" id="star4" name="star" value="4">★★★★<br>
					<input type="radio" id="star3" name="star" value="3">★★★<br>
					<input type="radio" id="star2" name="star" value="2">★★<br>
					<input type="radio" id="star1" name="star" value="1">★
				</c:when>
				<c:when test="${starPoint == 5}">
					<input type="radio" id="star5" name="star" value="5">★★★★★<br>
					<input type="radio" id="star4" name="star" value="4" checked>★★★★<br>
					<input type="radio" id="star3" name="star" value="3">★★★<br>
					<input type="radio" id="star2" name="star" value="2">★★<br>
					<input type="radio" id="star1" name="star" value="1">★
				</c:when>
				<c:when test="${starPoint == 5}">
					<input type="radio" id="star5" name="star" value="5">★★★★★<br>
					<input type="radio" id="star4" name="star" value="4">★★★★<br>
					<input type="radio" id="star3" name="star" value="3" checked>★★★<br>
					<input type="radio" id="star2" name="star" value="2">★★<br>
					<input type="radio" id="star1" name="star" value="1">★
				</c:when>
				<c:when test="${starPoint == 5}">
					<input type="radio" id="star5" name="star" value="5">★★★★★<br>
					<input type="radio" id="star4" name="star" value="4">★★★★<br>
					<input type="radio" id="star3" name="star" value="3">★★★<br>
					<input type="radio" id="star2" name="star" value="2" checked>★★<br>
					<input type="radio" id="star1" name="star" value="1">★
				</c:when>
				<c:otherwise>
					<input type="radio" id="star5" name="star" value="5">★★★★★<br>
					<input type="radio" id="star4" name="star" value="4">★★★★<br>
					<input type="radio" id="star3" name="star" value="3">★★★<br>
					<input type="radio" id="star2" name="star" value="2">★★<br>
					<input type="radio" id="star1" name="star" value="1" checked>★
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td>구매후기</td>
			<td><input name="review" value="${review.review }"></td>
		</tr>
		<tr>
			<td>사진첨부</td>
			<td><input type="file" name="itemFile"></td>
		</tr>
		<tr>
			<td>한줄요약</td>
			<td><input name="summary" value="${review.summary }"></td>
		</tr>
		<tr>
			<td colspan=2 align="center"><input type="submit" value="수정하기"></td>
		</tr>
</table>
</form>
</body>
</html>