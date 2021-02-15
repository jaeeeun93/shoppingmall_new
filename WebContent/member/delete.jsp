<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/include/head.jsp"%>
<!-- css연결 -->
<link rel="stylesheet" href="/css/withdraw.css">
<!-- font연결 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">

<div class="container">
	<div class="container2">
	
		<form name="deleteform" action="/member/Delete.do" method="post" onSubmit="return check2()">
		
			<div class="withdraw">회원 탈퇴</div>

			<div class="id">
				<div >
					<span style="color: red">*</span>아이디
				</div>
				<div style="width: 73px"></div>
				<div>
					<input type="text" id="id" name="id" maxlength="12" />
				</div>
			</div>

			<div class="pass">
				<div>
					<span style="color: red">*</span>비밀번호
				</div>
				<div style="width: 57px"></div>
				<div>
					<input type="password" name="pass" id="pass" onchange="check_pw()">
				</div>
			</div>

			<div class="passok">
				<div>
					<span style="color: red">*</span>비밀번호 확인
				</div>
				<div style="width: 21px"></div>
				<div>
					<input type="password" name="pass2" id="checkpass" onchange="check_pw()">&nbsp;<span id="check"></span>
				</div>
			</div>

			<div class="submit">
				<div>
					<input type="submit" value="회원탈퇴" onclick="if(confirm('정말로 탈퇴하시겠습니까?')){loacation.href='/member/Delete.do'}" />
				</div>
				<div style="width: 20px"></div>
				<div class="cancel">
					<input type="button" onclick="location.href='/'" value="취소" />
				</div>
			</div>	
		</div>
	</form>
</div>


<script>
	function check2() {

		if (!document.deleteform.id.value) {
			alert("아이디를 입력하지 않으셨습니다.");
			deleteform.id.focus();
			return false;
		}
		if (!document.deleteform.pass.value) {
			alert("비밀번호를 입력하지 않으셨습니다.");
			deleteform.pass.focus();
			return false;
		}
		if (!document.deleteform.pass2.value) {
			alert("비밀번호 확인을 입력하지 않으셨습니다.");
			deleteform.pass2.focus();
			return false;
		}
	}

	function check_pw() {

		if (document.getElementById('pass').value != ''
				&& document.getElementById('checkpass').value != '') {
			if (document.getElementById('pass').value == document
					.getElementById('checkpass').value) {
				document.getElementById('check').innerHTML = '비밀번호가 일치합니다.'
				document.getElementById('check').style.color = 'blue';

			} else {
				document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다.';
				document.getElementById('check').style.color = 'red';
			}
		}
	}
</script>
<%@include file="/include/foot.jsp"%>