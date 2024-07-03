<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="app.card.dao.CardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
<link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
<link rel="shortcut icon" type="image/x-icon" href="${cp}/app/img/favicon.ico">
<link rel="stylesheet" href="${cp}/css/common.css">
<link rel="stylesheet" href="${cp}/css/pointContainer.css">
<link rel="stylesheet" href="${cp}/css/index.css">
<link rel="stylesheet" href="${cp}/css/ticket.css">
<link rel="stylesheet" href="${cp}/css/footer.css">
<link rel="stylesheet" href="${cp}/css/tour_ticket/tourTicket.css">
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<title>마이리얼트립 :: 투어ㆍ티켓</title>
</head>
<body>
<header id="mainHeader">
    <div class="headerGnb">
      <nav class="globalNavBar">
        <div class="logo">
          <a href="${cp}/index.jsp"><img src="https://dffoxz5he03rp.cloudfront.net/icons/logo_mrt_web_white.svg" alt="logo"></a>
        </div>
        
        <!-- 로그인 정보 있을 경우  -->
        <c:if test="${loginUser == null}">
	        <div class="globalItems">
	        	<div class="globalItem">
	        		<a href="https://www.myrealtrip.com/about/becomeguide">
	        		파트너 등록하기
	        		</a>
	        	</div>
	        	<div class="globalItem">
	        		<a href="${cp}/user/sign_in.us">
	        		로그인
	        		</a>
	        	</div>
	        	<div class="globalItem signInItem">
	        		<a style="color:#fff;" href="${cp}/user/sign_up.us">
	        		회원가입
	        		</a>
	        	</div>
	       </div>
        </c:if>
        <c:if test="${loginUser != null}">
	        <!-- globalItems는 세션에 로그인 정보가 있어야 창을 보여줌 -->
	        <div class="globalItems" >
	          <div class="globalItem">
	            <a href="#" style="color:#fff;">위시리스트</a>
	          </div>
	          <div class="globalItem">
	            <a href="${cp}/mytrip/mytrip.mt" style="color:#fff;">내 여행</a>
	          </div>
	          <div class="globalItem">
	            <a href="#" style="color:#fff;">메시지</a>
	          </div>
	          <div class="globalItem">
	            <button onclick="location.href='#'">알림</button>
	          </div>
	          <div class="profileItem">
	            <div class="profileCard">
	              <button onclick="myinfo()">
	                <img src="${cp}/app/img/bird.png" alt="bird">
	              </button>
	            </div>
	                 <div class="myinfoContainer">
		            	<span class="smallArrow"></span>
		            	<div class="ProfilePopup-InfoSection">
			            	<div class="ProfileCardButton-Container">
			            		<div class="ProfileCardButton">
			            			<img src="http://d2yoing0loi5gh.cloudfront.net/assets/default/user_profile_image-414acc60b27f0a258bec14c82b70dc361fc6768da9289f924f887bec1fc33849.png">
			            		</div>
			            		<div class="DetailContainer ">
			            			<span>${loginUser.username}</span>
			            		</div>
			            	</div>
		            	</div>
		            	<div class="myinfo">
		            		<a href="${cp}/user/log_out.us">
		            			<span class="ProfilePopupLink">로그아웃</span>
		            		</a>
		            	</div>
		            </div>
	          </div>
	        </div>
        </c:if>
      </nav>
    </div>
    <div class="headerLnb">
      <nav class="localNavBar">
        <div class="lnbWrapper">
          <div class="localItemWithIcon">
            <a href="${cp}/index.jsp">
              <span>홈</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="${cp}/air/airReserve.ar">
              <span>항공권</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>투어·티켓</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="${cp}/hotel/hotel.ht">
              <span>국내숙소</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>호캉스</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>렌터카</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>패키지</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>한인민박</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>키즈</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="#">
              <span>할인혜택</span>
            </a>
          </div>
        </div>
      </nav>
    </div>
  </header>
  <!-- main Section start -->
  <main>
  	<div id="experience-component">
  		<div class="experience-container">
  		<!-- search & cityCard section start-->
  			<section class="experience-header">
  				<div class="experience-header-search">
  					<div class="global-search-container">
  						<div  id="searchContainer" class="search-input-container">
  							<div class="search-input-icon">
  								<img class="search-icon" src="https://dffoxz5he03rp.cloudfront.net/icons/ic_search_sm_white.svg">
  							</div>
  							<input id="searchInput" placeholder="도시나 상품을 검색해보세요" class="search-input">
  						</div>
  					</div>
  				</div>
  				<!-- cityCard -->
  				<div class="Grid-module_container">
  					<div class="Grid-module_row">
  						<div class="Carousel-module_container">
  							<div class="swiper-city">
  								<div class="swiper-wrapper">
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4737_medium_square_1535949304.jpg?1535949304');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>제주도</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" 
  									style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4654_medium_square_1535621335.jpg?1535621335');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>라스베가스</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4747_medium_square_1536047752.jpg?1536047752');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>파리</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4744_medium_square_1535960572.jpg?1535960572');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>서울</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4750_medium_square_1536049651.jpg?1536049651');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>런던</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4751_medium_square_1536050273.jpg?1536050273');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>로마</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/612_medium_square_1472534485.jpg?1472534485');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>마드리드</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4711_medium_square_1535695390.jpg?1535695390');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>피렌체</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4652_medium_square_1535619901.jpg?1535619901');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>방콕</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/4672_medium_square_1535678422.jpg?1535678422');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>바르셀로나</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/510_medium_square_1459151465.jpg?1459151465');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>로스엔젤레스</span>
  										</h3>
  									</a>
  									<a class="swiper-slide ExperiencePopularCityItem" style="background-image: url('https://d2ur7st6jjikze.cloudfront.net/landscapes/725_medium_square_1482978918.jpg?1482978918');">
  										<div class="ExperiencePopularCityItem_wrapper"></div>
  										<h3 class="ExperiencePopularCityTxt">
  											<span>톨레도</span>
  										</h3>
  									</a>
  								</div>
  							</div>
  							<button type="button" class="Eperience-button-prev">
  								<img src="${cp}/app/img/arrow_prev.svg">
  							</button>
  							<button type="button" class="Eperience-button-next">
  								<img src="${cp}/app/img/arrow_next.svg">  							
  							</button>
  						</div>
  					</div>
  				</div>  				
  			</section>
  		<!-- search & cityCard section end-->
  		<!-- advertise banner section start-->
  			<section class="mainBanner">
  				<%@ include file="/app/card/mainbanner.jsp" %>
  			</section>
  		<!-- advertise banner section end-->
  		<!-- main ticket card section start -->
  			<div class="content">
				<%@ include file="/app/card/ticket.jsp" %>  	    	 	
    		</div>
  		<!-- main ticket card section end -->
  		</div>
  	</div>
  </main>
  <!-- main Section end -->
  <!-- footer Section start -->
  <div class="default-components" id="footer-components">
		<footer id="footer" class="footer-container">
  			<%@ include file="/app/common/footer.jsp" %>	
 		</footer>
	</div>
  <!-- footer Section  end-->
</body>
<script src="${cp}/js/designForInput.js"></script>
<script src="${cp}/js/swiper_container.js"></script>
<script src="${cp}/js/myinfo.js"></script>
</html>