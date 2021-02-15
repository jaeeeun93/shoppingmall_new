/* 변수 선언 */

var code = "";

var id = document.querySelector('#id');
var pw = document.querySelector('#pswd1');

var error = document.querySelectorAll('.error_next_box');

/* 유효성 검사 통과유무 변수 */
var idCheck = false;            	// 아이디
var pwCheck = false;				// 비밀번호

/*이벤트 핸들러 연결*/

id.addEventListener("focusout", checkId);
pw.addEventListener("focusout", checkPw);

$(document).ready(function(){

	$(".login_button").click(function(){
	
		/* 입력값 변수 */
		var id = $('.id_input').val();                 // id 입력란
        var pw = $('.pw_input').val();                // 비밀번호 입력란
		
		/* 아이디 유효성검사 */
        if(id == ""){
            idCheck = false;
        }else{
            idCheck = true;
        }
	
		 /* 비밀번호 유효성 검사 */
        if(pw == ""){
            pwCheck = false;
        }else{
            pwCheck = true;
        }
        
        /* 최종 유효성 검사 */
        if(idCheck&&pwCheck){
 			$("#login").attr("action", "login_ok.do");
			$("#login").submit();
        }if(!idCheck){
			error[0].innerHTML = "필수 정보입니다.";
			error[0].style.color = "#F00";
			error[0].style.display = "block";
        }if(!pwCheck){
        	error[1].innerHTML = "필수 정보입니다.";
       		error[1].style.color = "#F00";
       		error[1].style.display = "block";
        }else {
        	
        }
        return false;
	});
});

/*콜백 함수*/

function checkId() {
    var idPattern = /[a-zA-Z0-9_-]{5,20}/;
    if(id.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
        idCheck = false;
    } else if(!idPattern.test(id.value)) {
        error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
        error[0].style.display = "block";
        idCheck = false;
    } else {
        error[0].style.display = "none";
        idCheck = true;
    }
}

function checkPw() {
    var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if(pw.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        error[1].style.display = "block";
        pwCheck = false;
    } else if(!pwPattern.test(pw.value)) {
        error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        error[1].style.display = "block";
        pwCheck = false;
    } else {
        error[1].style.display = "none";
        pwCheck = true;
    }
}

id.onchange = checkId;
pw.onchange = checkPw;