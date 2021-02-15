<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/include/head.jsp"%>

<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URI"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%
  request.setCharacterEncoding("UTF-8"); //받아올 데이터의 인코딩
  String name = request.getParameter("name");
  String price = request.getParameter("price");
  String file_s = request.getParameter("file_s");
%>

<style>
div.cart{
	display:inline-block;
}
</style>

<script>

function all_checked(sw) {
    var f = document.cart;

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
        alert("상품을 하나 이상 선택하세요.");
        return false;
    }

	if (!confirm("선택한 상품을 주문하시겠습니까?"))
	    return false;
	
	f.removeAttribute("target");
	f.action = "cartOrderInfo.do?str="+chk_str;

    return true;
}

</script>
<script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">

<div style="height:140px;"></div>
<div class = "cart" style="width:100%; float:left;">
<form name="cart" action="cartOrderInfo.do" method="post" enctype="multipart/form-data" onsubmit="return send(this);">
<table width=100% height=300 border=1 cellspacing=0>
<c:choose>
<c:when test="${count == 0}">
	<tr>
		<td colspan=8 align=center style="font-weight:bold; background-color:lightgray;">장바구니</td>
	</tr>
	<tr>
			
		<td align="center" style="font-weight:bold"><input type="checkbox" onclick="if (this.checked) all_checked(true); else all_checked(false);" checked></td>
		<td align="center" style="font-weight:bold">No.</td>
		<td align="center" style="font-weight:bold">사진</td>
		<td align="center" style="font-weight:bold">물품명</td>
		<td align="center" style="font-weight:bold">수량</td>
		<td align="center" style="font-weight:bold">상품가격</td>
		<td colspan=3 align="center" style="font-weight:bold">선택</td>
	</tr>
	<tr>
		<td colspan=7 align="center">장바구니에 담은 상품이 없습니다.</td>
	</tr>
	<tr>
		<td colspan=7 align=center><button type="button" onclick="location.href='/admin/item/list.do'">쇼핑하기</button></td>
	</tr>
</c:when>

<c:otherwise>
	<tr>
		<td colspan=8 align=center style="font-weight:bold; background-color:lightgray;">장바구니</td>
	</tr>
	<tr>
			
		<td align="center" style="font-weight:bold">
			<input type="checkbox" onclick="if (this.checked) all_checked(true); else all_checked(false);" checked>
		</td>
		<td align="center" style="font-weight:bold">No.</td>
		<td align="center" style="font-weight:bold">사진</td>
		<td align="center" style="font-weight:bold">물품명</td>
		<td align="center" style="font-weight:bold">수량</td>
		<td align="center" style="font-weight:bold">상품가격</td>
		<td colspan=3 align="center" style="font-weight:bold">선택</td>
	</tr>
	
	 <c:set var = "session_id" value = "<%=session_id %>"/>
	 <c:set var = "name" value = "<%=name %>"/>
	 
	<c:set var="number" value="${number}"/> 
	<c:forEach var="list" items="${v}">
		<tr>
			<td align=center><input type="checkbox" name="chk[]" value="${list.cartUid }" checked></td>
			<td align=center><input name="number" value="${number}" readonly></td>
			<input type="hidden" name="cartUid" value="${list.cartUid }">
			<td align=center><input type="image" src="/upload/${list.cartFile_s}" onclick="return false";></td>
			<td align=center><input name="itemName" value="${list.itemName}" readonly></td>
			<td align=center><input name="quantity" value="1" readonly></td>
			<td align=center><input name="itemPrice" value="<fmt:formatNumber type='number' maxFractionDigits='3' value='${list.itemPrice}'/>" readonly></td>
			<td align=center>
				<input type=button value="삭제" onclick="if(confirm('장바구니에서 물건을 삭제하시겠습니까?')) 
				{location.href='cart_delete.do?uid=${list.cartUid }&id=<%=session_id%>'}"><br>
			</td>
			<input type="hidden" name="id" value="${list.id}" readonly>
			<input type="hidden" name="itemPoint" value="${list.itemPoint}" readonly>
			<input type="hidden" name="stock" value="${list.stock}" readonly>
			<input type="hidden" name="cartFile_s" value="${info.itemFile_s}">
			<input type="hidden" name="itemCode" value="${info.itemCode}">
		</tr>
		
		<c:set var="number" value="${number-1}"/>
	</c:forEach>
	
	<%-- <tr>
		<td style="background-color:#d3d3d3" height=50 align="right" colspan=8>총 상품가격 <fmt:formatNumber type="number" maxFractionDigits="3" value="${price }"/>원 + 배송비 0원 = 총 주문금액 <span style="color:red;"><fmt:formatNumber type="number" maxFractionDigits="3" value="${price }"/></span>원</td>
	</tr> --%>
	<tr>
		<td colspan=4 align=right><button type="button" onclick="location.href='/admin/item/list.do'">계속 쇼핑하기</button></td>
		<td colspan=3 align=left><input type="submit" value="바로구매"></td>
	</tr>
</c:otherwise>
</c:choose>
</table>
</form>
</div>
<br>
<table width=100%>
	<tr>
		<td align=center>
		
<!-- 페이징 처리 -->		
<c:if test="${count  > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" />
	<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true"/>
	
	<!-- 2개의 변수 초기화 -->
	<c:set var="startPage" value="${1 }"/>
	<c:set var="pageBlock" value="${3 }"/>
	
	<!-- 다음 페이지 블럭이 존재 할 경우 startPage 값 변경 부분 -->
	<c:if test="${pageNum > pageBlock }">
		<!-- 결과 값을 정수형으로 리턴 받아야 하기 때문에 fmt 처리 -->
		<fmt:parseNumber var="result" value="${pageNum / pageBlock - (pageNum % pageBlock == 0 ? 1 : 0) }" integerOnly="true"/>
		<c:set var="startPage" value="${result * pageBlock + 1 }"/>
	</c:if>
	
	<!-- endPage 설정 -->
	<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
	<c:if test="${endPage > pageCount }">
		<c:set var="endPage" value="${pageCount }"/>
	</c:if>
	
	<!-- 이전 링크 -->
	<c:if test="${startPage > pageBlock }">
		<a href="/admin/item/cartlist.do?id=<%=session_id %>&pageNum=${startPage - pageBlock }">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="/admin/item/cartlist.do?id=<%=session_id %>&pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 링크 -->
	<c:if test="${endPage < pageCount}">
		<a href="/admin/item/cartlist.do?id=<%=session_id %>&pageNum=${startPage + pageBlock}">[다음]</a>
	</c:if>
</c:if>
		</td>
	</tr>
</table>

<%@include file="/include/foot.jsp"%>