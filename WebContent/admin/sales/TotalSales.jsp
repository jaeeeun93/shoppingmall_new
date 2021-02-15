<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="/admin/include/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="./jquery-ui-1.12.1/jquery-ui.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="./jquery-ui-1.12.1/jquery-ui.min.js"></script>

<script src="./jquery-ui-1.12.1/datepicker-ko.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#date2").datepicker({
		onSelect:function(dateText, inst) {
			console.log(dateText);
			console.log(inst);
		}
	});
	
	$("#date3").datepicker({
		onSelect:function(dateText, inst) {
			console.log(dateText);
			console.log(inst);
		}
	});
	$("#date4").datepicker({
		onSelect:function(dateText, inst) {
			console.log(dateText);
			console.log(inst);
		}
	});
	$("#date5").datepicker({
		onSelect:function(dateText, inst) {
			console.log(dateText);
			console.log(inst);
		}
	});
});

function Page_Load(){
	   var date2 = $("#date2").val();

	   location.href = "/Sale.do?date2="+date2+"";
}

function Page_Load2(){
	   var date3 = $("#date3").val();
	   var date4 = $("#date4").val();

	   location.href = "/Sale2.do?date3="+date3+"&date4="+date4+"";
}

function Page_Load3(){
	   var date5 = $("#date5").val();

	   location.href = "/Sale3.do?date5="+date5+"";
}
</script>


<table width=90% border=1 cellspacing=0 align=center>
  <tr>
    <td width=50% align=center>
    	<h2>총 주문수 : ${orderCount}</h2>
    </td>
    <td width=50% align=center>
    	<h2>총 매출액 : <fmt:formatNumber value="${totalSale}" type="currency"/></h2>
	</td>
  </tr>
</table>


<table border=1>
	<tr>
		<td>일일 매출</td>
		<td><input type="text" name="date2" id="date2" size="12" />일 하루 <button onclick="Page_Load();return false">확인</button></td>
		
	</tr>
	<tr>
		<td>일간 매출</td>
		<td><input type="text" name="date3" id="date3" size="12" />일 ~ <input type="text" name="date4" id="date4" size="12" />일 <button onclick="Page_Load2();return false">확인</button></td>
	</tr>
	<tr>
		<td>월간 매출</td>
		<td><input type="text" name="date5" id="date5" size="12" />월 <button onclick="Page_Load3();return false">확인</button></td>
	</tr>
</table>

<%@ include file="/admin/include/foot.jsp" %>