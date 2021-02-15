<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="java.sql.*"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URI"%>
<!-- css적용 -->
<link rel="stylesheet" href="/css/main.css">
<!-- 폰트 적용 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">

<%
Date today = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
String day = (String)(sdf.format(today));

String session_id = (String)session.getAttribute("id");
String session_name = (String)session.getAttribute("name");
String session_level = (String)session.getAttribute("level");
String session_item = (String)(sdf.format(today));

String session_guest = (String)session.getAttribute("session_guest");
if(session_guest == null){
	session.setAttribute("session_guest",day);
}
%>
<% 
    Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://localhost:3306/camp?serverTimezone=UTC";
	String user="root";
	String password="1111";
    
    request.setCharacterEncoding("utf-8");
	
    String id2 = session_id;
    String sql = "select count(*) as count from cart where id = '"+id2+"'";
    
    Connection conn = DriverManager.getConnection(url, user, password);
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	int total = 0;
	while (rs.next()) {
		total= rs.getInt("count");
		// db에서 읽은 총 장바구니 상품 수 넣음
	}

	rs.close();
	stmt.close();
	conn.close();	// 총 주문 상품 수 끝
	
	
	String id3 = session_id;
    String sql2 = "select count(*) as count from order2 where id = '"+id3+"'";
    
    Connection conn2 = DriverManager.getConnection(url, user, password);
	Statement stmt2 = conn2.createStatement();
	ResultSet rs2 = stmt2.executeQuery(sql2);
	
	int total2 = 0;
	while (rs2.next()) {
		total2= rs2.getInt("count");
		// db에서 읽은 총 주문 상품 수 넣음
	}

	rs2.close();
	stmt2.close();
	conn2.close();	// 총 장바구니 상품 수 끝
	
	
	// 받은 쪽지 개수 카운팅
	String id4 = session_id;
    String sql4 = "select count(*) as count from message where recv_id = '"+id4+"' and recv_del = 0";
    
    Connection conn4 = DriverManager.getConnection(url, user, password);
	Statement stmt4 = conn4.createStatement();
	ResultSet rs4 = stmt4.executeQuery(sql4);
	
	int total4 = 0;
	while (rs4.next()) {
		total4= rs4.getInt("count");
		
	}

	rs4.close();
	stmt4.close();
	conn4.close();
	
	// 보낸 쪽지 개수 카운팅
	String id5 = session_id;
    String sql5 = "select count(*) as count from message where send_id = '"+id5+"' and send_del = 0";
    
    Connection conn5 = DriverManager.getConnection(url, user, password);
	Statement stmt5 = conn5.createStatement();
	ResultSet rs5 = stmt5.executeQuery(sql5);
	
	int total5 = 0;
	while (rs5.next()) {
		total5= rs5.getInt("count");
		
	}

	rs5.close();
	stmt5.close();
	conn5.close();
	
	// 찜한 상품 개수 카운팅
	String id6 = session_id;
    String sql6 = "select count(*) as count from jjim where id = '"+id6+"' and jjim = 1";
    
    Connection conn6 = DriverManager.getConnection(url, user, password);
	Statement stmt6 = conn6.createStatement();
	ResultSet rs6 = stmt6.executeQuery(sql6);
	
	int total6 = 0;
	while (rs6.next()) {
		total6= rs6.getInt("count");
		
	}

	rs6.close();
	stmt6.close();
	conn6.close();
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<style>
img {
	border:0px;
}
.dropbtn {
  background-color: #FFFFFF;
  color: black;
  padding: 16px;
  font-size: 15px;
  border: none;
  z-index: 99
}

.dropdown {
  position: relative;
  display: inline-block;
	z-index: 99
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 99
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  z-index: 99
}
.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #3e8e41;}

/* 카테고리 */
#menu {
	width: 150px;
    height: 50px;
    background: #4285f4;
	margin-left : 10px;
	z-index: 99
}

ul{
	list-style:none;
	z-index: 99
}

.main1 {
    width: 300px;
    height: 100%;
    margin: 0 auto;
    z-index: 99
}

.main1>li {
    float: left;
    width: 25%;
    line-height: 50px;
    text-align: center;
    position: relative;
}

.main1>li:hover .main2 {
    left: 0;
}

.main1>li a {
    display: block;
}

.main1>li a:hover {
	background-color: #d49466;
    color: #fff;
    font-weight: bold;
}

.main2 {
    position: absolute;
    top: 50px;
    left: -9999px;
    background: #ccc;
    width: 140%;
    z-index: 99
}

.main2>li {
    position: relative;
}

.main2>li:hover .main3 {
    left: 100%;
}

.main2>li a, .main3>li a {
    border-radius: 10px;
    margin: 10px;
}

.main3 {
    position: absolute;
    top: -10;
    background: #ccc;
    width: 140%;
    left: -9999px;
    z-index: 99

    /*left: 100%;*/
    /*display: none;*/

}

.main3>li a:hover {
   background-color: #d49466;
    color: #fff;

}
/*여기서 부터 그리드스타일(영역) */

.container{
	display:inline;
	display:grid;
	grid-template-columns: repeat(3,1fr);
	grid-gap:1rem;
	justify-content: space-between;
	align-items: center;
	background-color:#263343;
	padding:8px 12px;
  	position: fixed; 
  	background-color: rgba(38,51,67,0.5);
  	width:100%;
  	height:120px;
  	margin:0;
  	background-color:gray;
	grid-template-columns: 200px 1fr;
	grid-gap:1fr;
}
.item:nth-child(1){
	grid-column:1/3;
}
.item:nth-child(2){
	grid-row:2/4;
}
.item1{
	position: absolute;
	left: 30%;
	top:5%;
	/* background-color:red; */
}
.item2{
	position: absolute;
	left: 5%;
	top:22%;
	width:100%;
}
.item3{
	position: absolute;
	left: 15%;
	top:34%;
}
.item5{
	position: absolute;
	left: 20%;
	top:77%;
	justify-content: space-between;
	width:60%;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container">
	<div class="item">
	 	<div class="item1" style="padding-right:30px;right:0">
			<%if(session_level != null && session_level.equals("10")){ %>
				<div align=right>
					<a style="padding-left:10px" href="/admin/index.jsp" target="_parent">[관리자]</a>
					<a style="padding-left:10px" href="/member/logout.do" target="_parent"> [로그아웃]</a>
					<a style="padding-left:10px" href="/member/member_modify.do" target="_parent"> [회원수정]</a>
				</div>
			<%}else if(session_id != null && !session_level.equals("10")){ %>
				<div align=right width = 40% style="padding-right:30px;">
					<div class="dropdown">
	  					<button class="dropbtn">쇼핑MY</button>
	  						<div class="dropdown-content">
	   				 			<a href="/order/orderCheck.do?id=<%=session_id %>" target="_parent">주문확인<br>배송조회</a>
	   				 			<a style="padding-left:10px" href="/review/reviewList.do?id=<%=session_id %>" target="_parent"> 구매후기</a>
	   				 			<a style="padding-left:10px" href="/jjimList.do?id=<%=session_id %>" target="_parent"> 찜<span style="color:red">(<%=total6 %>)</span></a>
	   				 			<a style="padding-left:10px" href="/couponList.do?id=<%=session_id %>" target="_parent"> 쿠폰함</a>
	 				 		</div>
					</div>
					
					<div class="dropdown">
	  					<button class="dropbtn">쪽지<span style="color:red">(<%=total4 %>)</span></button>
	  						<div class="dropdown-content">
	   				 			<a href="/message/messageForm.jsp" target="_parent">쪽지 쓰기</a>
	   				 			<a href="/message/messageReceive.do" target="_parent">받은쪽지함<span style="color:red">(<%=total4 %>)</span></a>
	   				 			<a href="/message/messageSend.do" target="_parent">보낸쪽지함<span style="color:red">(<%=total5 %>)</span></a>
	 				 		</div>
					</div>
					<a style="padding-left:10px" href="/admin/item/cartlist.do?id=<%=session_id%>" target="_parent">장바구니<span style="color:red">(<%=total %>)</span></a>				
					<a style="padding-left:10px" href="/member/logout.do" target="_parent">[로그아웃]</a>
					<a style="padding-left:10px" href="/member/member_modify.do" target="_parent">[회원수정]</a>
				</div>
						
			<%}else{ %>	
				<div align=right style="padding-left:10px">
					<a style="padding-left:10px" href="/member/login.jsp" target="_parent">[로그인]</a>
					<a style="padding-left:10px" href="/member/join.jsp" target="_parent">[회원가입]</a>
				</div>
			<%} %>
		</div>
	</div>
	
	<div class="item">
		<div class="item2" >
	 		<div id="menu" style="width:99px;height:65px;	border-radius:4px;">
		    <ul class="main1" >
		        <li><class="a" a href="#" style="width:65px;">카테고리</a>
		            <ul class="main2" style="width:160px;border-radius:4px;">
		                <li><a href="/admin/item/list.do">캠핑전문관</a>
		                    <ul class="main3" style="width:170px;border-radius:4px;">
		                        <li><a style="padding-left:10px" href="/category.do?category=campingtable">캠핑테이블</a></li>
		                        <li><a style="padding-left:10px" href="/category.do?category=ax">캠핑손도끼</a></li>
		                        <li><a style="padding-left:10px" href="/category.do?category=sleepingbag">침낭</a></li>
		                        <li><a style="padding-left:10px" href="/category.do?category=hammock">해먹</a></li>
		                        <li><a style="padding-left:10px" href="/category.do?category=etc">기타캠핑용품</a></li>
		                    </ul>
		                </li>
		            </ul>
		        </li>
		   	 </ul>
			</div>
		</div>
	</div>
	
	<div class="item">
	 	<div class="item3" style="display:flex;">
			<div class="logo" style="padding-left:130px;border:0px">
			<a href="/" target="_parent"><img src="/img/11.png" style="width:200px">
			</a>
			</div>
			<div style="width:100px;">
			</div>
			<div>
					<form action="/admin/item/SearchList.do" target="_parent" method="post">
						<div  style="text-align:center">
	    					<select name="field" style="width:150px;height:40px;">
	    						<option value="itemName">상품명</option>
	    					</select>&emsp;
	    					<input type="text" name="search" style="width:350px;height:40px" placeholder="찾고 싶은 상품을 검색해보세요!">&emsp;
	    					<input type="submit" value="검색" style="width:40px;height:40px">
			    		</div>
			    	</form>
			   </div>
		  </div> 
	</div>
	<div class="item">
	 	<div class="item5" style="justify-content: space-between; display:flex;padding-right:100px;">
			<%if(session_level != null && session_level.equals("10")){ %>
			<div align=center><a href="/mainPage.jsp" target="_parent">캠핑</a></div>
			<div align=center><a href="/admin/item/list.do" target="_parent">쇼핑</a></div>
			<div align=center><a href="/trade/List.do" target="_parent">중고거래</a></div>
			<div align=center><a href="/board/boardList.do" target="_parent">공지</a></div>
			<div align=center><a href="/campReview/review.jsp" target="_parent">캠핑장 리뷰</a></div>
			<div align=center><a href="/list/List.do" target="_parent">자유게시판</a></div>
			<%}else if(session_id != null && !session_level.equals("10")){ %>
				<div align=center><a href="/mainPage.jsp" target="_parent">캠핑</a></div>
			<div align=center><a href="/admin/item/list.do" target="_parent">쇼핑</a></div>
			<div align=center><a href="/admin/coupon/couponMake.jsp" target="_parent">쿠폰</a></div>
			<div align=center><a href="/Roulette.do" target="_parent">룰렛</a></div>
			<div align=center><a href="/trade/List.do" target="_parent">중고거래</a></div>
			<div align=center><a href="/board/boardList.do" target="_parent">공지</a></div>
			<div align=center><a href="/campReview/review.jsp" target="_parent">캠핑장 리뷰</a></div>
			<div align=center><a href="/list/List.do" target="_parent">자유게시판</a></div>
			<%}else{ %>
			<div align=center><a href="/mainPage.jsp" target="_parent">캠핑</a></div>
			<div align=center><a href="/camping/main.jsp" target="_parent">캠핑장</a></div>
			<div align=center><a href="/admin/item/list.do" target="_parent">쇼핑</a></div>
			<div align=center><a href="/admin/coupon/couponMake.jsp" target="_parent">쿠폰</a></div>
			<div align=center><a href="/trade/List.do" target="_parent">중고거래</a></div>
			<div align=center><a href="/board/boardList.do" target="_parent">공지</a></div>
			<div align=center><a href="/campReview/review.jsp" target="_parent">캠핑장 리뷰</a></div>
			<div align=center><a href="/list/List.do" target="_parent">자유게시판</a></div>
			<%} %>
		</div>
	</div>
</div>

</body>
</html>