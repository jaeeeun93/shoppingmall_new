<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div style="height:150px;"></div>
	<form action="" method="post" enctype="multipart/form-data">
		<table width=100% height=500 border=1 cellspacing=0>
			<c:set var="test" value="<%=total2%>" />
			<c:choose>
				<c:when test="${test == 0 }">
					<tr>
						<td align="center">구매후기 작성 가능한 구매 상품이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<th width=50%><a href="/review/reviewList.do?id=<%=session_id%>"><span style="color:#4285f4;">작성 가능 구매후기</span></a></th>
					<th width=50% colspan=2><a href="/review/wroteReview.do?id=<%=session_id%>"><span style="color:#4285f4;">내가 쓴 구매후기</span></a></th>
					<c:forEach var="list" items="${v}" varStatus="status">
						<c:set var="list2" value="${v[status.index]}" />
						<fmt:parseDate value="${list.reviewDate}" pattern="yyyyMMddhhmmss"
							var="date">
						</fmt:parseDate>
						<tr>
							<td align=center>
								<c:choose>
							<c:when test="${list.itemFile_s == ''}">
							<img src="/upload/noimg.jpg">
							</c:when>
							<c:otherwise>
								<a href="/admin/item/info.do?code=${list.itemCode}"><input type="image" name="itemFile" src="/upload/${list.itemFile_s }" onerror="this.src='/upload/no2.jpg'" readonly></a>
								</c:otherwise>
								</c:choose>
							</td>
							<td align=center>
								<input name="itemName" value="${list.itemName}" readonly><br>
							</td>
							<td align=center>
								<input type="button" value="수정" onclick="if(confirm('리뷰를 수정하시겠습니까?')){location.href='/review/reviewModify.do?uid=${list.reviewUid }&id=<%=session_id%>&code=${list.itemCode }&starPoint=${list.starPoint}'}">
								<input type=button value="삭제" onclick="if(confirm('리뷰를 삭제하시겠습니까?')){location.href='/review/reviewDelete.do?uid=${list.reviewUid }&id=<%=session_id%>&code=${list.itemCode }&starPoint=${list.starPoint}'}">
							</td>
						<tr>
							<td>
								<c:choose>
									<c:when test="${list.starPoint eq 5}">
										<span style="color: orange">★★★★★</span>
									</c:when>
									<c:when test="${list.starPoint eq 4}">
										<span style="color: orange">★★★★</span>
									</c:when>
									<c:when test="${list.starPoint eq 3}">
										<span style="color: orange">★★★</span>
									</c:when>
									<c:when test="${list.starPoint eq 2}">
										<span style="color: orange">★★</span>
									</c:when>
									<c:otherwise>
										<span style="color: orange">★</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td colspan=2>
								<input name="reviewDate" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly>
							</td>
						</tr>
						<tr>
							<td colspan=3>한줄평 : <input name="summary" value="${list.summary }"></td>
						</tr>
						<tr>
							<td colspan=3>후기 : <input name="review" value="${list.review }"></td>
						</tr>
						<tr>
							<td height=30 style="visibility: hidden;"></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
</body>
</html>