<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/include/head.jsp"%>
<!-- css연결 -->
<link rel="stylesheet" href="/css/modify.css">
<!-- font연결 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">

<div class="container">
	<div class="logo2">

		<form action="member_update.do" method="post">

				<div class="modify">회원 수정</div>

				<div class="id">
					<div>
						<span style="color: red">*</span>아이디
					</div>
					<div style="width: 70px"></div>
					<div>
						<input name="id" maxlength="16" value="${modify.id}" readonly>
					</div>
					<div style="width: 20px"></div>
					<div style="width: 5px"></div>
				</div>
				
				<div class="pass">
					<span style="color: red">*</span>비밀번호
					<div style="width: 53px"></div>
					<input name="pass" maxlength="16" value="${modify.pass}">
					<div style="width: 20px"></div>
					※ 4-12자의 영문 대소문자와 숫자로만 입력
				</div>

				<div class="name">
					<div>
						<span style="color: red">*</span>이름
					</div>
					<div style="width: 85px"></div>
					<div>
						<input name="name" value="${modify.name}">
					</div>
				</div>

				 <div class="email">
					<span style="color: red">*</span>메일주소
					<div style="width: 57px"></div>
					<input name="email" value="${modify.email}">
					<div style="width: 20px"></div>
					※ ex) 111a@google.com
				</div>

				<div class="phone">
					<span style="color: red">*</span>휴대폰 번호
					<div style="width: 38px"></div>
					<input name="phone" maxlength="16" value="${modify.phone}">
					<div style="width: 20px"></div>
					※ (-) 빼고 번호만 입력해주세요.
				</div>

				<div class="address">
					<div class="address1">
						주소
						<div style="width: 96px"></div>
						<input type="text" id="sample6_postcode" name="zipcode" value="${modify.zipcode}">
						<div style="width: 20px"></div>
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					</div>
					<div style="height: 10px"></div>
					<div class="address2">
						<div style="width: 127px; height: 10px"></div>
						<input type="text" id="sample6_address" name="zip1" value="${modify.zip1}">
					</div>
					<div style="height: 10px"></div>
					<div class="address3">
						<div style="height: 10px; width: 127px"></div>
						<input type="text" id="sample6_detailAddress" name="zip2" value="${modify.zip2}">
						<div style="width: 20px"></div>
						<input type="text" id="sample6_extraAddress" name="zip3" value="${modify.zip3}">
					</div>
				</div>

				<div class="submit">
					<div>
						<input type="submit" value="회원수정" />
					</div>
					<div style="width: 50px"></div>
					<div>
						<input type="button" value="회원탈퇴" onClick="location.href='/member/delete.jsp'"/>
					</div>
				</div>
			</div>
	</form>
</div>

	<script>
   function validate() {
       var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
       var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
       // 이메일이 적합한지 검사할 정규식
       var phonePattern = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;<!-- 2020.11.27추가 -->

       var id = document.getElementById("id");//id="이 값을 불러온다." id는 중복될수없다!
       var pass = document.getElementById("pass");
       var email = document.getElementById("email");
       var phone = document.getElementById("phone");<!-- 2020.11.27추가 -->

       if(id.value=="") {//id="id"를 불러와서 value값이 ""일 때 alret 뜨고 
           alert("아이디를 입력해 주세요");
           join.id.focus();//form이름이 join 이니까 그 안에 있는 id에 포커스줄수있게!그래야 다시 작성하기 편하니까
           return false;//한번 안되면 false되고 다음창으로 안돌아가게
       }
       if(join.idDuplication.value == "y" && join.idDuplication2.value=="n"){<!-- 중복체크여부 idDuplication  -->
    	   alert("사용할수없는 아이디입니다.");
    	  join.id.focus();
    	  return false;
       }
       
       if(join.idDuplication.value == "n" && join.idDuplication2.value=="n"){<!-- 아이디사용여부 idDuplication2-->
    	   alert("아이디 중복체크를 해주세요.");
    	   join.id.focus();
    	   return false;
       }
       
       if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
           join.id.focus();
    	   return false;
       }
		
       if(pass.value=="") {
           alert("비밀번호를 입력해 주세요");
           join.pass.focus();
           return false;
       }
       
       if(!check(re,pass,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {//정규식re랑 맞지 않을 때 fucntion check()를 이용해서 
           join.pass.focus();
    	   return false;
       }

       if(email.value=="") {
           alert("이메일을 입력해 주세요");
           email.focus();
           return false; 
       }

       if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
           return false;
       }

       if(join.name.value=="") {
           alert("이름을 입력해 주세요");
           join.name.focus();
           return false;
       }
       if(join.phone.value==""){
    	   alert("전화번호를 입력해주세요");
    	  join.phone.focus();
    	  return false;
       }
       <!-- 2020.11.27수정 -->
       if(!check(phonePattern, phone, "전화번호를 정확하게 입력")){
    	   join.phone.focus();
    	   return false;
       }
       <!-- 여기까지-->
       alert("회원가입이 완료되었습니다.");
   }

   function check(re, what, message) {
       if(re.test(what.value)) {
           return true;
       }
       alert(message);
       what.value = "";
       what.focus();
       return false;
   }
   
   function check_pw(){
	   
	   if(document.getElementById('pass').value !='' && document.getElementById('checkpass').value!=''){
           if(document.getElementById('pass').value==document.getElementById('checkpass').value){
               document.getElementById('check2').innerHTML='비밀번호가 일치합니다.'
               document.getElementById('check2').style.color='blue';
           }
           else{
               document.getElementById('check2').innerHTML='비밀번호가 일치하지 않습니다.';
               document.getElementById('check2').style.color='red';
           }
       }
   }
   
 //onkeydown 입력시
   function inputIdChk(){
       document.join.idDuplication.value ="n";//form 의 name값이 n이된다.onkeydown이 키보드를 눌렸을때 실행되는거라id값을 바꿨을수도잇아서
       document.join.idDuplication2.value ="n";
   }
   
   //중복확인-1.값받는 폼2. 서블렛.3.dao-db랑 연결
   function id_check(){//id_check 함수에 담는다
		var id2 = join.id.value;//form이름이 join이라 그안에 있는 id.value를 변수 id2에 담는다.
		
		window.open("idCheck.do?id_value="+id2+"","","width=460,height=130");//window.open팝업창 띄우는 구문
		//idCheck.do 서블렛으로 가는데 id_value라는 변수에 id값을 담은 변수 id2를 담는다.
	}
</script>

	<!-- 주소 -->

	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
	<%-- <%@include file="/include/foot.jsp"%> --%>