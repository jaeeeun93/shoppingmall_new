<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<body onload="init2();">
<script language='javascript'>

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

// 쿠폰

var sell_price;
var amount;

function init2 () {
	amount = document.orderInfo.amount.value;
	change();
}

function change () {
	var selectValue = document.getElementById('amount').value;
	totalPrice = document.orderInfo.totalPrice;
	totalPrice.value = ${price}-selectValue;
}  

</script>

</head>

<body>
<body oncontextmenu="return false">
<form name="orderInfo" action="" method="post">
<table width=100% height=800 border=1>
	<th colspan=6 style="font-weight:bold; background-color:lightgray;">구매자정보</th>
	<tr>
		<tr>
			<td>이름</td>
			<td colspan=5><input name="name" maxlength="16" value="${customer.name}" readonly></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td colspan=5><input name="email" maxlength="16" value="${customer.email}" readonly></td>
		</tr>
		<tr>
			<td>휴대폰 번호</td>
			<td colspan=5><input name="phone" maxlength="16" value="${customer.phone}" readonly></td>
		</tr>
		<input type="hidden" name="id" value="${customer.id }">
		<tr>
		</tr>
	</tr>

	<th colspan=6 style="font-weight:bold; background-color:lightgray;">받는사람정보</th>
	<tr>
		<tr>
			<td>이름</td>
			<td colspan=5><input id="cname" name="cname" maxlength="16"></td>
		</tr>
		<tr>
		<td rowspan=3>주소</td>
		<td colspan=5>
			<input type="text" id="sample6_postcode" name="zipcode">
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
		</td>
	</tr>
	<tr>
		<td colspan=5><input type="text" id="sample6_address" name="zip1"></td>
	</tr>
	<tr>
		<td colspan=5>
			<input type="text" id="sample6_detailAddress" name="zip2">
			<input type="text" id="sample6_extraAddress" name="zip3">
		</td>
	</tr>
	<tr>
		<tr>
			<td>연락처</td>
			<td colspan=5><input name="cphone" id="cphone" maxlength="16"></td>
		</tr>
		<tr>
			<td>배송 요청사항</td>
			<td colspan=5>
				<input type="radio" id="pick1" name="pick" value="문앞">문앞
				<input type="radio" id="pick2" name="pick" value="경비실">경비실
				<input type="radio" id="pick3" name="pick" value="택배함">택배함
				<input type="radio" id="pick4" name="pick" value="기타사항">기타사항
			</td>
		</tr>
	</tr>


<th colspan=6 style="font-weight:bold; background-color:lightgray;">배송물품</th>
	
	<c:forEach var="list" items="${v}">
	<tr>
		<td><input id="cartFile_s" type="image" name="cartFile_s" src="/upload/${list.cartFile_s }"></td>
		<td><input id="itemName" name="itemName" value="${list.itemName }"></td>
		<td><input id="itemPrice" name="itemPrice" value="${list.itemPrice }원"></td>
		<td><input id="itemPoint" name="itemPoint" value="${list.itemPoint}포인트"></td>
		<input type="hidden" id="cartUid" name="cartUid" value="${list.cartUid}">
		<input type="hidden" id="itemCode" name="itemCode" value="${list.itemCode}">
	</tr>
	</c:forEach>

<th colspan=6 style="font-weight:bold; background-color:lightgray;">결제정보</th>
	<tr>
		<tr>
			<td>주문금액</td>
			<td>${price }</td>
		</tr>
		<tr>
			<td>쿠폰</td>
			
			<td>
				쿠폰 금액<select id="amount" name="amount" onchange="change(this.value);">
				<c:forEach var="list2" items="${coupon}">
					<option value="${list2.coupon_name }">${list2.coupon_name }</option>
				</c:forEach>
				</select>
			</td>
			
		</tr>
		<tr>
			<td>총결제금액</td>
			<td colspan=5><input name="totalPrice"></td>
		</tr>
		<tr>
			<td>총적립포인트</td>
			<td colspan=5><input name="totalPoint" value="${point }포인트"></td>
		</tr>
		<tr>
			<td>결제방법</td>
			<td colspan=3><input type="radio" name="pay" value="카카오페이" checked>카카오 페이</td>
		</tr>
	</tr>
	<tr>
		<td colspan=4 align=center style="background-color:lightgray;">
			<!-- <input type=submit value="결제하기"> -->
			<button id="check_module" type="button">카카오페이로 결제하기</button>
		</td>
	</tr>
	<tr>
		<td colspan=4 align=center style="background-color:lightgray;"><button type="button" onclick="location.href='/order/OrderCancel.do?orderId=${orderId}'">쇼핑 목록으로 돌아가기</button></td>
	</tr>
</table>
</form>
<!-- 여기부터 카카오페이 결제 구현 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>



<script>
    $("#check_module").click(function () {
        var IMP = window.IMP; // 생략가능
        
        var total = document.orderInfo.totalPrice.value;
        var coupon = document.getElementById('amount').value;
        IMP.init('imp50695258'); // 내가 회원가입 때 받은거 넣기
        // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
        IMP.request_pay({
            pg: 'inicis', // version 1.1.0부터 지원.
            
            //    'kakao':카카오페이
            pay_method: 'card',
            /* 
                'samsung':삼성페이, 
                'card':신용카드, 
                'trans':실시간계좌이체,
                'vbank':가상계좌,
                'phone':휴대폰소액결제 
            */
            merchant_uid: 'merchant_' + new Date().getTime(),
            /* 
                merchant_uid에 경우 
                https://docs.iamport.kr/implementation/payment
                위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
             */
            name: '주문명:카카오페이 결제됨?',
            //결제창에서 보여질 이름
            amount: total, 
            //가격 
            buyer_email: 'jaeeeun93@gmail.com',
            buyer_name: '구매자이름',
            buyer_tel: '010-8230-9980',
            buyer_addr: '울산광역시 중구 종가로 730 107동 1001호',
            buyer_postcode: '123-456',
            //m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*  
                모바일 결제시,
                결제가 끝나고 랜딩되는 URL을 지정 
                (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐) 
                */
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                
                /* var cartUid = document.getElementName('cartUid').value;
                var carFile_s = document.getElementName('cartFile_s').value;
                var itemNmae = document.getElementName('itemName').value;
                var itemPrice = document.getElementName('itemPrice').value; */
                
                var cname = document.getElementById('cname').value;
                var zipcode = document.getElementById('sample6_postcode').value;
                var zip1 = document.getElementById('sample6_address').value;
                var zip2 = document.getElementById('sample6_detailAddress').value;
                var zip3 = document.getElementById('sample6_extraAddress').value;
                var cphone = document.getElementById('cphone').value;
                var orderId = ${orderId};
                
                var pick ="";
                if (orderInfo.pick1.checked == true) {
					pick = document.getElementById("pick1").value;
				}else if(orderInfo.pick2.checked == true){
					pick = document.getElementById("pick2").value;
				}else if(orderInfo.pick3.checked == true){
					pick = document.getElementById("pick3").value;
				}else{
					pick = document.getElementById("pick4").value;
				}
                
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                
                 location.href="/order/cartOrderBuy.do?id=${customer.id}&name=${customer.name}&email=${customer.email}&phone=${customer.phone}&cname="+cname+"&zipcode="+zipcode+"&zip1="+zip1+"&zip2="+zip2+"&zip3="+zip3+"&cphone="+cphone+"&totalPrice="+total+"&point=${customer.point}&orderId="+orderId+"&pick="+pick+"&coupon="+coupon+"&itemPoint=${point}";
            	
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                location.href="/order/OrderCancel.do?&orderId=${orderId}";
            }
            alert(msg);
        });
    });
    <!--카카오페이 결제 구현 끝-->  
    
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

</body>
</html>