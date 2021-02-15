<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/admin/include/head.jsp"%>

<table width=90% border=1 cellspacing=0 align=center>
	<tr>
		<td align=left>
			<a href="/admin/item/admin_item_insert.jsp">상품추가</a>
		</td>
		<td align=right>
			<select name="field">
				<option name="itemName" value="상품명">상품명
			</select>
			<input placeholder="검색창위치" name="search" style="width:250px;">
			<input type="button" value="검색" onclick="location.href=''">
		</td>
	</tr>
</table>
<br>
<table width=90% align=center>
	<tr>
		<td align=left>총 상품 갯수 : ${count}</td>
	</tr>
</table>
<table width=90% border=1 cellspacing=0 align=center>
	<tr align=center>
		<td width="9%">수정,삭제</td>
		<td width="9%">상품코드</td>
		<td width="5%">상품사진</td>
		<td width="10%">카테고리</td>
		<td>상품이름</td>
		<td width="9%">가격</td>
		<td width="9%">재고</td>
		<td width="10%" colspan=2>상태(판매,품절)</td>
	</tr>
	<c:forEach var="Aitem" items="${v}">
	<tr align=center>
		<td>
			<input type="button" value="상품수정" onclick="location.href='/admin/item/Modify.do?uid=${Aitem.itemUid}'">
			<input type="button" value="상품삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='/admin/item/itemDelete.do?uid=${Aitem.itemUid}'}">
		</td>
		<td>${Aitem.itemCode}</td>
		<td><img src="/upload/${Aitem.itemFile_s}"></td>
		<td>${Aitem.category}</td>
		<td>${Aitem.itemName}</td>
		<td><fmt:formatNumber value="${Aitem.itemPrice}" type="currency"/></td>
		<td>${Aitem.stock}</td>
		<td>
			<c:if test="${Aitem.sold eq '품절'}"><a style="color:red;">${Aitem.sold}</a></c:if>
			<c:if test="${Aitem.sold eq '판매'}">${Aitem.sold}</c:if>
		</td>
		<td>
			<c:if test="${Aitem.sold eq '판매'}">
				 <input type="button" value="품절" onclick="location.href='/admin/item/soldout.do?uid=${Aitem.itemUid}'">
			</c:if>
			<c:if test="${Aitem.sold eq '품절'}">
				 <input type="button" value="판매" onclick="location.href='/admin/item/sold.do?uid=${Aitem.itemUid}'">
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

<%@include file="/admin/include/foot.jsp"%>