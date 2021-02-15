<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/include/head.jsp"%>
<!-- css연결 -->
<link rel="stylesheet" href="/css/idsearch.css">
<!-- font연결 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">

	<nav class="container"><!-- 전체감싸는 -->
	
		<form name="idsearch" action="/member/IdSearch.do" method="post" onsubmit="return search();">
		<div class="container1">
				<div class="left">
					<div>
					<input type="button" value="ID Search" onclick="location.href='/member/idSearch.jsp'">
					</div>
				</div>
				<div class="right">
					<div>
					<input type="button" value="Password Search" onclick="location.href='/member/passSearch.jsp'">
					</div>
				</div>
		</div>
		<div style="border-bottom:1px solid black;">
		</div>
		<div class="container2">
			<div class="search">아이디 찾기</div>
			<div>
				<form name="idsearch" action="/member/IdSearch.do" method="post" onsubmit="return search();">
			</div>
			<div class="pass">
				<div class="passinput">
				<span style="color:red">*</span>비밀번호 입력
				</div>
				<div style="width:20px">
				</div>
				<div>
					<input type="password" name="pass" id="pass" maxlength="12" />
				</div>
			</div>
			<div class="email">
				<div>
					<span style="color:red">*</span>이메일 입력
				</div>
				<div style="width:36px">
				</div>
				<div>
					<input type="text" name="email" id="email" maxlength="20" />
				</div>
				 <div style="width:20px">
						</div>
					    <div>       	
					           	※ ex) 111a@google.com
					    </div>
			</div>
			<div class="phone">
				<div>
					<span style="color:red">*</span>휴대폰 번호
				</div>
				<div style="width:36px">
				</div>
				<div>
					<input name="phone" id="phone" maxlength="11" />
				</div>
				<div style="width:20px">
				</div>
				<div>
				 	※ (-) 빼고 번호만 입력해주세요.
				</div>
			</div>
			<div class="ok">
				<div>
					<input type="submit" value="아이디찾기">
				</div>
				<div style="width:20px">
				</div>
				<div class="re">
					<input type="reset" value="다시입력" />
				</div>
			</div>
		</div><!-- container2 크게 감싸는 -->
</nav>
<script>
	function search(){
		var i=idsearch;
		if(i.pass.value==""){
			alert("비밀번호를 입력하세요");
			i.pass.focus();
			return false;
		}
		if(i.email.value==""){
			alert("이메일을 입력하세요");
			i.email.focus();
			return false;
		}
		if(i.phone.value==""){
			alert("휴대폰 번호를 입력해주세요");
			i.phone.focus();
			return false;
		}
	}
</script>
<%-- <%@include file="/include/foot.jsp"%> --%>