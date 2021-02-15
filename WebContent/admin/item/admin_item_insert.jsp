<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/admin/include/head.jsp"%>

<form action="item_insert.do" method="post" enctype="multipart/form-data">
<table width=100%>
	<tr>
		<td colspan=2>상품등록</td>
	</tr>
	<tr>
		<td>상품이름</td>
		<td><input name="itemName"></td>
	</tr>
	<tr>
		<td>상품사진</td>
		<td><input type="file" name="itemFile"></td>
	</tr>
	<tr>
		<td>상품가격</td>
		<td><input name="itemPrice"></td>
	</tr>
	<tr>
		<td>상품포인트</td>
		<td><input name="itemPoint"></td>
	</tr>
	<tr>
		<td>재고</td>
		<td><input name="stock"></td>
	</tr>
	<tr>
		<td>카테고리</td>
		<td>
			<select name="category">
		    	<option value="campingtable">캠핑테이블</option>
		    	<option value="ax">캠핑손도끼</option>
		    	<option value="sleepingbag">침낭</option>
		    	<option value="hammock">해먹</option>
		    	<option value="etc">기타캠핑용품</option>
		    </select>
		</td>
	</tr>
		<input type="hidden" name="itemDate">
		<input type="hidden" name="itemCode">
	<tr>
		<td><input type="submit" value="상품등록"></td>
	</tr>
</table>
</form>



<%@ include file="/admin/include/foot.jsp" %>