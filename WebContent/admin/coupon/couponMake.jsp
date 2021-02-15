<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/include/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#image{
  margin:50px 400px;
  z-index:10;
}

#start_btn{position:absolute;left:600px;top:260px;z-index:20;}

.cbutton {
  background-color: white;
  border: none;
  color: white;
  padding: 10px 10px;
  margin: 140px -100px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
}

.cbutton2 {
  background-color: white;
  border: none;
  color: white;
  padding: 10px 10px;
  margin: 140px 230px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
}

.cbutton3 {
  background-color: white;
  border: none;
  color: white;
  padding: 10px 10px;
  margin: 140px 560px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
}

.cbutton4 {
  background-color: white;
  border: none;
  color: white;
  padding: 10px 10px;
  margin: 320px -100px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
}

.cbutton5 {
  background-color: white;
  border: none;
  color: white;
  padding: 10px 10px;
  margin: 320px 230px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
}

.cbutton6 {
 background-color: white;
  border: none;
  color: white;
  padding: 10px 10px;
  margin: 320px 560px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
}

div.cground{
	position: absolute;
	top : 100px;
	z-index : -3;
}

</style>

</head>
<body>
<div class="cground">
	<img src="/img/coupon.PNG" alt="" id="image">
</div>

	<button class="cbutton" onclick="if(confirm('쿠폰을 발급 받으시겠습니까?')){location.href='couponMake.do?coupon=3000'}" id='start_btn'><img src="/img/cbutton.PNG" alt=""></button>
	<button class="cbutton2" onclick="if(confirm('쿠폰을 발급 받으시겠습니까?')){location.href='couponMake.do?coupon=9000'}" id='start_btn'><img src="/img/cbutton.PNG" alt=""></button>
	<button class="cbutton3" onclick="if(confirm('쿠폰을 발급 받으시겠습니까?')){location.href='couponMake.do?coupon=2000'}" id='start_btn'><img src="/img/cbutton.PNG" alt=""></button>

	<button class="cbutton4" onclick="if(confirm('쿠폰을 발급 받으시겠습니까?')){location.href='couponMake.do?coupon=1000'}" id='start_btn'><img src="/img/cbutton.PNG" alt=""></button>
	<button class="cbutton5" onclick="if(confirm('쿠폰을 발급 받으시겠습니까?')){location.href='couponMake.do?coupon=4000'}" id='start_btn'><img src="/img/cbutton.PNG" alt=""></button>
	<button class="cbutton6" onclick="if(confirm('쿠폰을 발급 받으시겠습니까?')){location.href='couponMake.do?coupon=1500'}" id='start_btn'><img src="/img/cbutton.PNG" alt=""></button>

</body>
</html>
<%@include file="/include/foot.jsp"%>