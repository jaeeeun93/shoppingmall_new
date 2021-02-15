/* 변수 선언 */

var code = "";

var id = document.querySelector('#id');
var pw1 = document.querySelector('#pswd1');
var pwMsg = document.querySelector('#alertTxt');
var pwImg1 = document.querySelector('#pswd1_img1');
var pw2 = document.querySelector('#pswd2');
var pwImg2 = document.querySelector('#pswd2_img1');
var pwMsgArea = document.querySelector('.int_pass');

var userName = document.querySelector('#username');

var email = document.querySelector('#email');

var mobile = document.querySelector('#mobile');

var error = document.querySelectorAll('.error_next_box');
/* 유효성 검사 통과유무 변수 */
var idCheck = false;            	// 아이디
var idckCheck = false;           	// 아이디 중복 검사
var pwCheck = false;           		// 비번
var pwckCheck = false;           	// 비번 확인
var pwckcorCheck = false;        	// 비번 확인 일치 확인
var nameCheck = false;            	// 이름
var mailCheck = false;            	// 이메일
var addressCheck = false;        	// 주소
var mobileCheck = false;
var mobilenumCheck = false;


/*이벤트 핸들러 연결*/

id.addEventListener("focusout", checkId);
id.addEventListener("focusout", idChk);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);



$(document).ready(function(){

	$(".join_button").click(function(){
		
		/* 입력값 변수 */
        var id = $('.id_input').val();                 // id 입력란
        var pw = $('.pw_input').val();                // 비밀번호 입력란
        var pwck = $('.pwck_input').val();            // 비밀번호 확인 입력란
        var name = $('.user_input').val();            // 이름 입력란
        var email = $('.mail_input').val();            // 이메일 입력란
        var addr = $('.address_input_3').val();        // 주소 입력란
        var mobile = $('.mobile_input').val();							// 휴대전화 입력란
	
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
        
        /* 비밀번호 확인 유효성 검사 */
        if(pwck == ""){
            pwckCheck = false;
        }else{
            pwckCheck = true;
        }
        
        /* 이름 유효성 검사 */
        if(name == ""){
            nameCheck = false;
        }else{
            nameCheck = true;
        }
        
        
        /* 이메일 유효성 검사 */
        if(email == ""){
            mailCheck = false;
        }else{
            mailCheck = true;
        }
        
        /* 휴대전화 유효성 검사 */
        if(mobile == ""){
            mobileCheck = false;
        }else{
            mobileCheck = true;
        }
        
        /* 최종 유효성 검사 */
        if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&mailCheck&&mobileCheck&&mobilenumCheck){
 			$("#join").attr("action", "/member/member_insert.do");
			$("#join").submit();
        }if(!idCheck){
			error[0].innerHTML = "필수 정보입니다.";
			error[0].style.color = "#F00";
			error[0].style.display = "block";
        }if(!pwCheck){
        	error[1].innerHTML = "필수 정보입니다.";
       		error[1].style.color = "#F00";
       		error[1].style.display = "block";
        }if(!pwckCheck){
        	error[2].innerHTML = "필수 정보입니다.";
       		error[2].style.color = "#F00";
       		error[2].style.display = "block";
        }if(!nameCheck){
        	error[3].innerHTML = "필수 정보입니다.";
       		error[3].style.color = "#F00";
       		error[3].style.display = "block";
        }if(!mailCheck){
        	error[4].innerHTML = "필수 정보입니다.";
       		error[4].style.color = "#F00";
       		error[4].style.display = "block";
        }if(!mobileCheck){
        	error[5].innerHTML = "필수 정보입니다.";
       		error[5].style.color = "#F00";
       		error[5].style.display = "block";
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
        error[0].innerHTML = "사용 가능한 아이디입니다";
        error[0].style.color = "#08A600";
        error[0].style.display = "block";
        idCheck = true;
    }
}

function idChk(){
	var userid = id.value;
	var data = {userid : userid}
	
	$.ajax({
		type : "post",
		url : "/member/idChk",
		data : data,
		success : function(result){
			if(result != 'fail'){
				idckCheck = true;
       		} else {
       			 error[0].innerHTML = "중복 아이디 입니다.";
       			 error[0].style.color = "#F00";
       			 error[0].style.display = "block";
       			 idckCheck = false;
       		}
		}
		
	});
}

function checkPw() {
    var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if(pw1.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        error[1].style.display = "block";
        pwCheck = false;
    } else if(!pwPattern.test(pw1.value)) {
        error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        pwMsg.innerHTML = "사용불가";
        pwMsgArea.style.paddingRight = "93px";
        error[1].style.display = "block";
        
        pwMsg.style.display = "block";
        pwImg1.src = "/img/m_icon_not_use.png";
        pwCheck = false;
    } else {
        error[1].style.display = "none";
        pwMsg.innerHTML = "안전";
        pwMsg.style.display = "block";
        pwMsg.style.color = "#03c75a";
        pwImg1.src = "/img/m_icon_safe.png";
        pwCheck = true;
    }
}

function comparePw() {
    if(pw2.value === pw1.value && pw2.value != "") {
        pwImg2.src = "/img/m_icon_check_enable.png";
        error[2].style.display = "none";
        pwckcorCheck = true;
    } else if(pw2.value !== pw1.value) {
        pwImg2.src = "/img/m_icon_not_use.png";
        error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[2].style.display = "block";
        pwckcorCheck = false;
    } 

    if(pw2.value === "") {
        error[2].innerHTML = "필수 정보입니다.";
        error[2].style.display = "block";
        pwckcorCheck = false;
    }
}

function checkName() {
    var namePattern = /[a-zA-Z가-힣]/;
    if(userName.value === "") {
        error[3].innerHTML = "필수 정보입니다.";
        error[3].style.display = "block";
        nameCheck = false;
    } else if(!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
        error[3].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
        error[3].style.display = "block";
        nameCheck = false;
    } else {
        error[3].style.display = "none";
        nameCheck = true;
    }
}


function isEmailCorrect() {
    var emailPattern = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

    if(email.value === ""){ 
    	error[4].innerHTML = "필수 정보입니다.";
        error[4].style.display = "block";
        mailCheck = false; 
    } else if(!emailPattern.test(email.value)) {
    	error[4].innerHTML = "올바르지 못한 이메일 형식입니다.";
        error[4].style.display = "block";
        mailCheck = false;
    } else {
        error[4].style.display = "none";
        mailCheck = true;
    }

}

/* 입력 이메일 형식 유효성 검사 */
function mailFormCheck(email){
	var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i; 
	return form.test(email);
}

function checkPhoneNum() {
    var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;

    if(mobile.value === "") {
        error[5].innerHTML = "필수 정보입니다.";
        error[5].style.display = "block";
        mobilenumCheck = false;
    } else if(!isPhoneNum.test(mobile.value)) {
        error[5].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[5].style.display = "block";
        mobilenumCheck = false;
    } else {
        error[5].style.display = "none";
        mobilenumCheck = true;
    }

    
}



/*

var id = document.querySelector('#id');
var pw1 = document.querySelector('#pswd1');
var pw2 = document.querySelector('#pswd2');
var email = document.querySelector('#email');
var mobile = document.querySelector('#mobile');
var error = document.querySelectorAll('.error_next_box');

var pattern_num = /[0-9]/;
var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/;


id.onchange = checkId;
pw1.onchange = checkPw;
pw2.onchange = comparePw;
yourName.onchange = checkName;
yy.onchange = checkYear;
email.onchange = isEmailCorrect;
mobile.onchange = checkPhoneNum;

function checkId() {
    if(id.value === "") {
        error[0].style.display = "block";
    } else if(id.value.length < 5 || id.value.length > 20){
        error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
        error[0].style.display = "block";
    }
}

function checkPw() {
    if(pw1.value === "") {
        error[1].style.display = "block";
    } else if (pw1.value.length < 8 || pw1.value.length > 16) {
        error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        error[1].style.display = "block";
    }
}

function comparePw() {
    if(pw2.value === "") {
        error[2].style.display = "block";
    } else if (pw2.value !== pw1.value) {
        error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[2].style.display = "block";
    }
}

function checkName() {
    if( yourName.value.indexOf(" ") >= 0 || pattern_spc.test(yourName.value) || pattern_num.test(yourName.value) ) {
        error[3].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
        error[3].style.display = "block";
    } else if(yourName.value.length === 0) {
        error[3].style.display = "block";
    } else {
        error[3].style.display = "none";
    }
}

function checkEmail() {
    
}

function checkNumber() {
    
}
*/