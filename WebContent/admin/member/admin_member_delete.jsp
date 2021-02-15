<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/include/head.jsp"%>

<form action="/admin/member/Withdraw.do" method="post">
<table width=100%>
	<tr>
		<td align="center" style="font-weight:bold">아이디</td>
		<td align="center" style="font-weight:bold">비밀번호</td>
		<td align="center" style="font-weight:bold">이름</td>
		<td align="center" style="font-weight:bold">이메일</td>
		<td align="center" style="font-weight:bold">레벨</td>
		<td align="center" style="font-weight:bold">주소</td>
		<td align="center" style="font-weight:bold">전화번호</td>
		<td align="center" style="font-weight:bold">포인트</td>
		<td align="center" style="font-weight:bold">쿠폰</td>
		<td align="center" style="font-weight:bold">탈퇴여부</td>
	</tr>
	<tr>
		<td><input name="id" value="${modify.id}" readonly></td>
		<td><input name="pass" value="${modify.pass}" readonly></td>
		<td><input name="name" value="${modify.name}"></td>
		<td><input name="email" value="${modify.email}"></td>
		<td><input name="level" value="${modify.level}"></td>
		<td align=center width=10%>
			<input name="zipcode" value="${modify.zipcode}">
			<input name="zip1" value="${modify.zip1}">
			<input name="zip2" value="${modify.zip2}">
			<input name="zip3" value="${modify.zip3}">
		</td>
		<td><input name="phone" value="${modify.phone}"></td>
		<td><input name="point" value="${modify.point}" readonly></td>
		<td><input name="coupon" value="${modify.coupon}" readonly></td>
		<td><input name="userDel" value="${modify.userDel}" readonly></td>
	</tr>
	<tr>
		<td colspan=10 align=center><input type="submit" value="회원삭제"></td>
	</tr>
</table>
</form>

<%@include file="/include/foot.jsp"%>