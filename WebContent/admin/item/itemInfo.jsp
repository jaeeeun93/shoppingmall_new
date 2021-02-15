<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/include/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
  request.setCharacterEncoding("UTF-8"); //받아올 데이터의 인코딩
  String code = request.getParameter("code"); //리스트에서 썸네일 사진 클릭시 넘겨 준 코드값
  String name = request.getParameter("name");
%>

<style>
div.itemInfo{
	display:inline-block;
}
</style>

<script>
  function check() {
    var id = "<%=session_id%>";
    var level = "<%=session_level%>";
    
    if ( level == null) {
      alert( '비회원은 장바구니 사용이 불가합니다' );
      return false;
    }
    else{
    	if(confirm('장바구니에 담으시겠습니까?')){
    		
    	}else{
    		return false;
    	} 
    	
    }
    return true;
  }
</script>
<script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
<div style="height:140px;"></div>
<div class = "itemInfo" style="width:100%; float:left;">
<form action="cart_insert.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
<table width=100% height=700 border=1>
	<tr>
		<td rowspan=3 align=center>
			<c:if test="${info.sold eq '판매' && info.stock != 0}">
				<input name="itemFile" type="image" src="/upload/${info.itemFile}"></td>
			</c:if>
			<c:if test="${info.sold eq '품절' || info.stock eq 0}">
				<img src="/upload/${info.itemFile}">
			</c:if>
		</td>
			<input type="hidden" name="cartFile_s" value="${info.itemFile_s}">
		<td>
			<input name="itemName" value="${info.itemName}" readonly><br>
				<c:choose>
 					<c:when test="${avg eq 5}">
 						<span style="color:orange">★★★★★(${avg })</span>
 					</c:when>
 					<c:when test="${avg >= 4}">
 						<span style="color:orange">★★★★☆(${avg })</span>
 					</c:when>
					<c:when test="${avg >= 3}">
 						<span style="color:orange">★★★☆☆(${avg })</span>
 					</c:when>
 					<c:when test="${avg >= 2}">
 						<span style="color:orange">★★☆☆☆(${avg })</span>
 					</c:when>
 					<c:when test="${avg >= 1}">
 						<span style="color:orange">★☆☆☆☆(${avg })</span>
 					</c:when>
 					<c:otherwise>
 						<span style="color:orange">☆☆☆☆☆(${avg })</span>
 					</c:otherwise>
				</c:choose>
				${count}개의 상품평
		</td>
		<input type="hidden" name="itemCode" value="${info.itemCode}">
		<input type="hidden" name="id" value="<%=session_id%>">
		<input type="hidden" name="session" value="<%=session_guest%>">
	</tr>
	<tr>
		<td>
			<c:if test="${info.sold eq '판매' && info.stock != 0}">
				<input name="itemPrice" value="${info.itemPrice}" readonly size=3>원
			</c:if>
			<c:if test="${info.sold eq '품절' || info.stock eq 0}">
				<p style="color:red;">품절</p>
			</c:if>
		</td>
	</tr>
	<tr>	
		<td>
			<c:if test="${info.sold eq '판매' && info.stock != 0}">
				<input name="itemPoint" value="${info.itemPoint}" readonly size=3>포인트
			</c:if>
			<c:if test="${info.sold eq '품절' || info.stock eq 0}">
				<p style="color:red;">품절</p>
			</c:if>
		</td>
	</tr>
		<input type="hidden" name="stock" value="${info.stock}" readonly>
	<tr>
		<td colspan=5 align=center>
			<c:if test="${info.sold eq '판매' && info.stock != 0}">
				<input type="submit" value="장바구니 담기">
				<input type="button"onclick="if(confirm('바로 구매 하시겠습니까?')){location.href='orderInfo.do?itemName=${info.itemName}&itemCode=${info.itemCode}&cartFile_s=${info.itemFile_s}&session=<%=session_guest%>&itemPrice=${info.itemPrice }&itemPoint=${info.itemPoint}'}" value="바로구매">
			</c:if>
			<c:if test="${info.sold eq '품절' || info.stock eq 0}">
				<p style="color:red;">품절</p>
			</c:if>
		</td>
	</tr>
</table>
</form>
</div>

<table width=100% height=500 border=1 cellspacing=0>
<c:set var="test" value="${count }"/>
<c:choose>
<c:when test="${test == 0 }">
<tr>
	<td align="center">작성된 리뷰가 없습니다.</td>
</tr>		
</c:when>
<c:otherwise>
	<c:forEach var="list" items="${v}" varStatus="status">
	<c:set var="list2" value="${v[status.index]}"/>
	<fmt:parseDate value="${list.reviewDate}" pattern ="yyyyMMddhhmmss" var="date"> </fmt:parseDate>
		<tr>
			<td>
				<c:choose>
 					<c:when test="${list.starPoint eq 5}">
 						<i class ="fas fa-user-circle" style="font-size:40px"></i>
 						<c:out value="${fn:replace(list.name,fn:substring(list.name,1,2),'*')}"/><br>
 						<span style="color:orange">★★★★★</span>
 						<input name="reviewDate" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly>
 					</c:when>
 					<c:when test="${list.starPoint eq 4}">
 						<i class ="fas fa-user-circle" style="font-size:40px"></i>
 						<c:out value="${fn:replace(list.name,fn:substring(list.name,1,2),'*')}"/><br>
 						<span style="color:orange">★★★★☆</span>
 						<input name="reviewDate" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly>
 					</c:when>
					<c:when test="${list.starPoint eq 3}">
						<i class ="fas fa-user-circle" style="font-size:40px"></i>
						<c:out value="${fn:replace(list.name,fn:substring(list.name,1,2),'*')}"/><br>
 						<span style="color:orange">★★★☆☆</span>
 						<input name="reviewDate" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly>
 					</c:when>
 					<c:when test="${list.starPoint eq 2}">
 						<i class ="fas fa-user-circle" style="font-size:40px"></i>
 						<c:out value="${fn:replace(list.name,fn:substring(list.name,1,2),'*')}"/><br>
 						<span style="color:orange">★★☆☆☆</span>
 						<input name="reviewDate" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly>
 					</c:when>
 					<c:otherwise>
 						<i class ="fas fa-user-circle" style="font-size:40px"></i>
 						<c:out value="${fn:replace(list.name,fn:substring(list.name,1,2),'*')}"/><br>
 						<span style="color:orange">★☆☆☆☆</span>
 						<input name="reviewDate" value="<fmt:formatDate value='${date}' pattern='yyyy/MM/dd'></fmt:formatDate>" readonly>
 					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan=2><input name="itemName" value="${list.itemName}" readonly><br></td>
		</tr>
		<tr>	
			<td colspan=2 align=center>
				<c:choose>
					<c:when test="${list.itemFile_s == '' }">
					<img src="/upload/noimg.jpg">
					</c:when>
					<c:otherwise>
				<a href="/admin/item/info.do?code=${list.itemCode}"><input type="image" name="" src="/upload/${list.itemFile_s }" onerror="this.src='/upload/no2.jpg'" readonly></a>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		
		<tr>
			<td colspan=3>한줄평 : <input name="summary" value="${list.summary }"></td>
		</tr>
		<tr>
			<td colspan=3>후기 : <input name="review" value="${list.review }"></td>
		</tr>
		<tr>
			<td height=30 style="visibility:hidden;"></td>
		</tr>
	</c:forEach>
</c:otherwise>
</c:choose>
</table>
<div style="height:140px;"></div>
<%@include file="/include/foot.jsp"%>