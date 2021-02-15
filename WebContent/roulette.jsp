<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>룰렛</title>
<meta charset="utf-8">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script> 
<script type="text/javascript" src="./js/jQueryRotateCompressed.js"></script>
<style>
#image{
  margin:50px 600px;
  z-index:10;
}
#n_id{position:absolute;left:840px;top:240px;z-index:20;}
#start_btn{position:absolute;left:600px;top:260px;z-index:20;}

.button {
  background-color: blue;
  border: none;
  color: white;
  padding: 50px 50px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 130px 205px;
  cursor: pointer;
  opacity :0;
}

div.point{
	position: absolute;
	top : 160px;
	
}

div.roulette{
	position: absolute;
	top : 150px;
	
}

div.niddle{
	position: absolute;
	top : -70px;
	left : 0px;
}
</style>
</head>
<body>

<div class="point">
	내 포인트 : ${member.point }
</div>

<div class="niddle">
	<img src="img/niddle.png" id="n_id">
</div>
<div class="roulette">
<img src="img/roulette.png" id="image">
</div>

<br/>
<button class="button" type="button" value='시작' id='start_btn' style=""></button>
<div id="result_id"></div>
<div id="result_id2"></div>
<div id="result_id3"></div>

<script language="javascript">

function noEvent() {
	if (event.keyCode == 116) {
	event.keyCode= 2;
	return false;
	}
	else if(event.ctrlKey && (event.keyCode==78 || event.keyCode == 82))
	{
	return false;
	}
	}
	document.onkeydown = noEvent;

window.onload = function(){
	
	var pArr = ["500","1000","2500","3500","4500","5000","6000","7000","8000","9000"];

	$('#start_btn').click(function(){
		rotation();
	});

	function rotation(){
		$("#image").rotate({
		  angle:0, 
		  animateTo:360 * 5 + randomize(0, 360),
		  center: ["50%", "50%"],
		  easing: $.easing.easeInOutElastic,
		  callback: function(){ 
						var n = $(this).getRotateAngle();
						endAnimate(n);
					},
		  duration:5000
	   });
	}

	function endAnimate($n){
		var n = $n;
		var real_angle = n%360 +18;
		var part = Math.floor(real_angle/36);

		if(${member.point } < 1000){
			alert("포인트가 부족합니다. 1000포인트 필요!")
			return false;
		}
		
		if(part < 1){
			alert("당첨 : "+ pArr[0]+"원 쿠폰")
			location.href="RouletteAction.do?point="+pArr[0]+"";
			return;
		}

		if(part >= 10){
			alert("당첨 : " + pArr[pArr.length-1]+"원 쿠폰")
			location.href="RouletteAction.do?point="+pArr[pArr.length-1]+"";
			return;
		}

		alert("당첨 : " + pArr[part]+"원 쿠폰")
		location.href="RouletteAction.do?point="+pArr[part]+"";
	}

	function randomize($min, $max){
		return Math.floor(Math.random() * ($max - $min + 1)) + $min;
	}
};
</script>

<%@ include file="/include/foot.jsp"%>
</body>
</html>
