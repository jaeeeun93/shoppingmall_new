<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://kit.fontawesome.com/d01610d97f.js" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Amado - Furniture Ecommerce Template | Shop</title>

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
                    <li class="active"><a href="/shop.jsp">Shop</a></li>
                    <li><a href="/product-details.jsp">Product</a></li>
                    <li><a href="/cart.jsp">Cart</a></li>
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

        <div class="shop_sidebar_area">

            <!-- ##### Single Widget ##### -->
            <div class="widget catagory mb-50">
                <!-- Widget Title -->
                <h6 class="widget-title mb-30">Catagories</h6>

                <!--  Catagories  -->
                <div class="catagories-menu">
                    <ul>
                        <li class="active"><a href="/admin/item/list.do">전체</a></li>
                        <li><a href="/category.do?category=campingtable">캠핑테이블</a></li>
                        <li><a href="/category.do?category=ax">손도끼/야전삽</a></li>
                        <li><a href="/category.do?category=sleepingbag">침낭</a></li>
                        <li><a href="/category.do?category=hammock">해먹</a></li>
                        <li><a href="/category.do?category=lantern">랜턴</a></li>
                        <li><a href="/category.do?category=stove">난로</a></li>
                        <li><a href="/category.do?category=etc">기타캠핑용품</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="amado_product_area section-padding-100">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-12">
                        <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                            <!-- Total Products -->
                            <div class="total-products">
                            </div>
                            <!-- Sorting -->
                            <div class="product-sorting d-flex">
                                <div class="sort-by-date d-flex align-items-center mr-15">
                                    <p>Sort by</p>
                                    <form action="#" method="get">
                                        <select name="select" id="sortBydate">
                                            <option value="value">Low</option>
                                            <option value="value">High</option>
                                            <option value="value">Volume</option>
                                        </select>
                                    </form>
                                </div>
                                <div class="view-product d-flex align-items-center">
                                    <p>View</p>
                                    <form action="#" method="get">
                                        <select name="select" id="viewProduct">
                                            <option value="value">12</option>
                                            <option value="value">24</option>
                                            <option value="value">48</option>
                                            <option value="value">96</option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                	<c:set var="number" value="${number}"/>
                    <c:forEach var="list" items="${v}" varStatus="status">
                    
                    <!-- Single Product Area -->
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                    
                            <!-- Product Image -->
                            <div class="product-img">
                                <a href="/admin/item/info.do?code=${list.itemCode}&name=${list.itemName }"><img src="/upload/${list.itemFile}" alt=""></a>
                                <!-- Hover Thumb -->
                                <!-- <img class="hover-img" src="/upload/${list.itemFile}" alt=""> -->
                            </div>

                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p class="product-price">${list.itemPrice }원</p>
                                    <a href="/admin/item/info.do?code=${list.itemCode}&name=${list.itemName }">
                                        <h6>${list.itemName }</h6>
                                    </a>
                                </div>
                                <!-- Ratings & Cart -->
                                <div class="ratings-cart text-right">
                                    <div class="ratings">
                                       <c:choose>
											<c:when test="${list.starPoint == 0}">
						 						<span style="color:orange">☆☆☆☆☆(0)</span>
						 					</c:when>
						 					<c:when test="${list.starPoint / list.pnum eq 5}">
						 						<span style="color:orange">★★★★★(${list.pnum })</span>
						 					</c:when>
						 					<c:when test="${list.starPoint / list.pnum >=4}">
						 						<span style="color:orange">★★★★☆(${list.pnum})</span>
						 					</c:when>
											<c:when test="${list.starPoint / list.pnum >=3}">
						 						<span style="color:orange">★★★☆☆(${list.pnum })</span>
						 					</c:when>
						 					<c:when test="${list.starPoint / list.pnum >=2}">
						 						<span style="color:orange">★★☆☆☆(${list.pnum })</span>
						 					</c:when>
						 					<c:when test="${list.starPoint / list.pnum >=1}">
						 						<span style="color:orange">★☆☆☆☆(${list.pnum })</span>
						 					</c:when>
						 					<c:otherwise>
						 						<span style="color:orange">☆☆☆☆☆(0개의 리뷰)</span>
						 					</c:otherwise>
										</c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <c:set var="number" value="${number-1}"/>    
	                </c:forEach>
                </div>

                <div class="row">
                    <div class="col-12">
                        <!-- Pagination -->
                        <nav aria-label="navigation">
                            <ul class="pagination justify-content-end mt-50">
                            <!-- 페이징 처리 -->		
								<c:if test="${count  > 0}">
									<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" />
									<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true"/>
									
									<!-- 2개의 변수 초기화 -->
									<c:set var="startPage" value="${1 }"/>
									<c:set var="pageBlock" value="${3 }"/>
									
									<!-- 다음 페이지 블럭이 존재 할 경우 startPage 값 변경 부분 -->
									<c:if test="${pageNum > pageBlock }">
										<!-- 결과 값을 정수형으로 리턴 받아야 하기 때문에 fmt 처리 -->
										<fmt:parseNumber var="result" value="${pageNum / pageBlock - (pageNum % pageBlock == 0 ? 1 : 0) }" integerOnly="true"/>
										<c:set var="startPage" value="${result * pageBlock + 1 }"/>
									</c:if>
									
									<!-- endPage 설정 -->
									<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
									<c:if test="${endPage > pageCount }">
										<c:set var="endPage" value="${pageCount }"/>
									</c:if>
									
									<!-- 이전 링크 -->
									<c:if test="${startPage > pageBlock }">
										<li class="page-item"><a class="page-link" href="/admin/item/list.do?pageNum=${startPage - pageBlock }">&lt;</a></li>
									</c:if>
									
									<!-- 페이지 출력 -->
									<c:forEach var="i" begin="${startPage }" end="${endPage }">
										<li class="page-item"><a class="page-link" href="/admin/item/list.do?pageNum=${i }">[${i }]</a></li>
									</c:forEach>
									
									<!-- 다음 링크 -->
									<c:if test="${endPage < pageCount}">
										<li class="page-item"><a class="page-link" href="/admin/item/list.do?pageNum=${startPage + pageBlock}">&gt;</a></li>
									</c:if>
								</c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
                


</div>
                
                
                
                
                
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
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a> & Re-distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
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