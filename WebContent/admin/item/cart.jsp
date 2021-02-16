<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="java.sql.*"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.net.URI"%>
<script src="https://kit.fontawesome.com/d01610d97f.js" crossorigin="anonymous"></script>
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
<html lang="en">

<%
  request.setCharacterEncoding("UTF-8"); //받아올 데이터의 인코딩
  String name = request.getParameter("name");
  String price = request.getParameter("price");
  String file_s = request.getParameter("file_s");
%>

<script>
function all_checked(sw) {
    var f = document.cart;

    for (var i=0; i<f.length; i++) {
        if (f.elements[i].name == "chk[]")
            f.elements[i].checked = sw;
    }
}

function send(f) {
    var chk_count = 0;
	var chk_str = "";
	
    for (var i=0; i<f.length; i++) {
        if (f.elements[i].name == "chk[]" && f.elements[i].checked == true){
            chk_count++;
        	chk_str += f.elements[i].value+"-";
        }
    }

    if (!chk_count) {
        alert("상품을 하나 이상 선택하세요.");
        return false;
    }

	if (!confirm("선택한 상품을 주문하시겠습니까?"))
	    return false;
	
	f.removeAttribute("target");
	f.action = "cartOrderInfo.do?str="+chk_str;

    return true;
}

</script>
<script type="text/javascript">
 window.history.forward();
 function noBack(){window.history.forward();}
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">


<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Amado - Furniture Ecommerce Template | Cart</title>

    <!-- Favicon  -->
    <link rel="icon" href="/img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="/css/core-style.css">
    <link rel="stylesheet" href="/style.css">

</head>

<body>
    <!-- Search Wrapper Area Start -->
    <div class="search-wrapper section-padding-100">
        <div class="search-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="search-content">
                        <form action="#" method="get">
                            <input type="search" name="search" id="search" placeholder="Type your keyword...">
                            <button type="submit"><img src="/img/core-img/search.png" alt=""></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Search Wrapper Area End -->

    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="/index.jsp"><img src="/img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Navbar Toggler -->
            <div class="amado-navbar-toggler">
                <span></span><span></span><span></span>
            </div>
        </div>

        <!-- Header Area Start -->
        <header class="header-area clearfix">
            <!-- Close Icon -->
            <div class="nav-close">
                <i class="fa fa-close" aria-hidden="true"></i>
            </div>
            <!-- Logo -->
            <div class="logo">
                <a href="/index.jsp"><img src="/img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
                <ul>
                    <li><a href="/index.jsp">Home</a></li>
                    <li><a href="/shop.jsp">Shop</a></li>
                    <li><a href="/product-details.jsp">Product</a></li>
                    <li class="active"><a href="/cart.jsp">Cart</a></li>
                    <li><a href="/checkout.jsp">Checkout</a></li>
                </ul>
            </nav>
            <!-- Button Group -->
            <div class="amado-btn-group mt-30 mb-100">
                <a href="#" class="btn amado-btn mb-15">%Discount%</a>
                <a href="#" class="btn amado-btn active">New this week</a>
            </div>
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
                <a href="/cart.jsp" class="cart-nav"><img src="/img/core-img/cart.png" alt=""> Cart <span>(0)</span></a>
                <a href="#" class="fav-nav"><img src="/img/core-img/favorites.png" alt=""> Favourite</a>
                <a href="#" class="search-nav"><img src="/img/core-img/search.png" alt=""> Search</a>
            </div>
            <!-- Social Button -->
            <div class="social-info d-flex justify-content-between">
                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
        </header>
        <!-- Header Area End -->
		
        <div class="cart-table-area section-padding-100">
        <form name="cart" action="cartOrderInfo.do" method="post" enctype="multipart/form-data" onsubmit="return send(this);">
		<c:choose>
		<c:when test="${count == 0}">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="cart-title mt-50">
                            <h2>Shopping Cart</h2>
                        </div>

                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" onclick="if (this.checked) all_checked(true); else all_checked(false);" checked></th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="cart_product_img">
                                        </td>
                                        <td class="cart_product_desc">담은 상품이 없습니다
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                            <h5>Cart Total</h5>
                            <ul class="summary-table">
                                <li><span>subtotal:</span> <span>0원</span></li>
                                <li><span>delivery:</span> <span>Free</span></li>
                                <li><span>total:</span> <span>0원</span></li>
                            </ul>
                            <div class="cart-btn mt-100">
                                <a href="/admin/item/list.do" class="btn amado-btn w-100">쇼핑하기</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </c:when>
        
        <c:otherwise>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="cart-title mt-50">
                            <h2>Shopping Cart</h2>
                        </div>

                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                    	<th><input type="checkbox" onclick="if (this.checked) all_checked(true); else all_checked(false);" checked></th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                    </tr>
                                </thead>
                                
                                <c:set var = "session_id" value = "<%=session_id %>"/>
	 							<c:set var = "name" value = "<%=name %>"/>
                                
                                <c:set var="number" value="${number}"/> 
								<c:forEach var="list" items="${v}">
                                
                                <tbody>
                                    <tr>
                                        <td class="cart_product_img">
                                        	<input type="checkbox" name="chk[]" value="${list.cartUid }" checked>
                                            <input type="image" src="/upload/${list.cartFile_s}" onclick="return false";>
                                        	<input type="hidden" name="cartUid" value="${list.cartUid }">
                                        </td>
                                        <td class="cart_product_desc">
                                            <span><input name="itemName" value="${list.itemName}"></span>
                                        </td>
                                        <td class="price">
                                            <span><input name="itemPrice" value="${list.itemPrice}"></span>
                                        </td>
                                        <td class="qty">
                                            <div class="qty-btn d-flex">
                                                <p>수량</p>
                                                <div class="quantity">
                                                    <input type="number" name="quantity" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="1">
                                                </div>
                                                	<input type=button value="삭제" onclick="if(confirm('장바구니에서 물건을 삭제하시겠습니까?')){location.href='cart_delete.do?uid=${list.cartUid }&id=<%=session_id%>'}">
                                            </div>
                                        </td>
                                        <input type="hidden" name="id" value="${list.id}" readonly>
										<input type="hidden" name="itemPoint" value="${list.itemPoint}" readonly>
										<input type="hidden" name="stock" value="${list.stock}" readonly>
										<input type="hidden" name="cartFile_s" value="${info.itemFile_s}">
										<input type="hidden" name="itemCode" value="${info.itemCode}">
                                    </tr>
                                </tbody>
                                <c:set var="number" value="${number-1}"/>
								</c:forEach>
                                
                                
                            </table>
                        </div>
                    </div>
                    
                    <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                            <h5>Cart Total</h5>
                            <ul class="summary-table">
                                <li><span>subtotal:</span> <span>${price }</span></li>
                                <li><span>delivery:</span> <span>Free</span></li>
                                <li><span>total:</span> <span>${price }</span></li>
                            </ul>
                            <div class="cart-btn mt-100">
                                <a href="/admin/item/list.do" class="btn amado-btn w-100">계속 쇼핑하기</a>
                            </div>
                            <div class="cart-btn mt-100">
                                <input type="submit" class="btn amado-btn w-100" value="구매하기">
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </c:otherwise>
        </c:choose>
        </form>
        
        </div>
    </div>
    <!-- ##### Main Content Wrapper End ##### -->

    <!-- ##### Newsletter Area Start ##### -->
    <section class="newsletter-area section-padding-100-0">
        <div class="container">
            <div class="row align-items-center">
                <!-- Newsletter Text -->
                <div class="col-12 col-lg-6 col-xl-7">
                    <div class="newsletter-text mb-100">
                        <h2>Subscribe for a <span>25% Discount</span></h2>
                        <p>Nulla ac convallis lorem, eget euismod nisl. Donec in libero sit amet mi vulputate consectetur. Donec auctor interdum purus, ac finibus massa bibendum nec.</p>
                    </div>
                </div>
                <!-- Newsletter Form -->
                <div class="col-12 col-lg-6 col-xl-5">
                    <div class="newsletter-form mb-100">
                        <form action="#" method="post">
                            <input type="email" name="email" class="nl-email" placeholder="Your E-mail">
                            <input type="submit" value="Subscribe">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Newsletter Area End ##### -->

    <!-- ##### Footer Area Start ##### -->
    <footer class="footer_area clearfix">
        <div class="container">
            <div class="row align-items-center">
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-4">
                    <div class="single_widget_area">
                        <!-- Logo -->
                        <div class="footer-logo mr-50">
                            <a href="/index.jsp"><img src="/img/core-img/logo2.png" alt=""></a>
                        </div>
                        <!-- Copywrite Text -->
                        <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->& Re-distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a>
</p>
                    </div>
                </div>
                <!-- Single Widget Area -->
                <div class="col-12 col-lg-8">
                    <div class="single_widget_area">
                        <!-- Footer Menu -->
                        <div class="footer_menu">
                            <nav class="navbar navbar-expand-lg justify-content-end">
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                                <div class="collapse navbar-collapse" id="footerNavContent">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item active">
                                            <a class="nav-link" href="/index.jsp">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/shop.jsp">Shop</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/product-details.jsp">Product</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/cart.jsp">Cart</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="/checkout.jsp">Checkout</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area End ##### -->

    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
    <script src="/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="/js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="/js/plugins.js"></script>
    <!-- Active js -->
    <script src="/js/active.js"></script>

</body>

</html>