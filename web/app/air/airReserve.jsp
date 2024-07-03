<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
 <link rel="shortcut icon" type="image/x-icon" href="${cp}/app/img/favicon.ico">
 <link rel="stylesheet" href="${cp}/css/air/airReserve.css">
 <link rel="stylesheet" href="${cp}/css/air/air.css"> 
 <link rel="stylesheet" href="${cp}/css/footer.css">
 <link rel="stylesheet" href="${cp}/css/air/airticket.css">
 <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<title>마이리얼트립 :: 항공권</title>
</head>
<body>
<header id="mainHeader">
    <div class="bgimg"></div>
    <div class="headerGnb">
      <nav class="globalNavBar">
        <div class="logo">
          <a href="${cp}/index.jsp"><img src="https://dffoxz5he03rp.cloudfront.net/icons/logo_mrt_web_white.svg" alt="logo"></a>
        </div>
        <div class="searchBar">
          <div  id="searchContainer" class="searchContainer">
            <div class="searchImg">
              <img class="SearchIcone__" src="${cp}/app/img/white_search.svg" alt="search">
            </div>
            <input id="searchInput" class="searchInput" type="text" placeholder="도시나 상품을 검색해보세요">
          </div>
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
	        		<a  href="${cp}/user/sign_up.us">
	        		회원가입
	        		</a>
	        	</div>
	       </div>
        </c:if>
        <c:if test="${loginUser != null}">
	        <!-- globalItems는 세션에 로그인 정보가 있어야 창을 보여줌 -->
	        <div class="globalItems">
	          <div class="globalItem">
	            <a href="#">위시리스트</a>
	          </div>
	          <div class="globalItem">
	            <a href="${cp}/mytrip/mytrip.mt">내 여행</a>
	          </div>
	          <div class="globalItem">
	            <a href="#">메시지</a>
	          </div>
	          <div class="globalItem">
	            <button onclick="location.href='#'">알림</button>
	          </div>
	          <div class="profileItem">
	            <div class="profileCard">
	              <button onclick="myinfo();">
	                <img src="${cp}/app/img/bird.png" alt="bird">
	              </button>
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
            <a href="#">
              <span>홈</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="${cp}/air/airReserve.ar">
              <span>항공권</span>
            </a>
          </div>
          <div class="localItemWithIcon">
            <a href="${cp}/tour/tourTicket.tr">
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
	    <%@ include file="/app/air/air.jsp" %>
      </nav>
    </div>
  </header>
<main style="height: 110vh;">
  	<section class="airBanner">
		<%@ include file="/app/air/airbanner.jsp" %>     	
    </section>
    <section class="airMain">
    
		<%@ include file="/app/air/airJeju_ticket.jsp" %>
	<div class="main-notice">
		<h2>항공권 공지사항</h2>
		<div class="notice-more">
			<a class="notice-more-btn">더보기</a>
		</div>
		<table class="air-board">
			<tbody class="rowlength">
				<tr>
					<td class="air-tal">
						<span class="air-board-contents">
						[공지] 2022년 6월 1일 부 유류할증료 인상 예정 안내
						</span>
					</td>
					<td class="time">2022-05-27</td>
				</tr>
				<tr>
					<td class="air-tal">
						<span class="air-board-contents">
						[공지] 티웨이항공(TW) 비즈니스 좌석 판매 안내
						</span>
					</td>
					<td class="time">2022-05-27</td>
				</tr>
				<tr>
					<td class="air-tal">
						<span class="air-board-contents">
						[공지] 거리두기 격상에 따른 국내선 항공권 환불 요청 방법
						</span>
					</td>
					<td class="time">2022-05-27</td>
				</tr>
			</tbody>
		</table>
	</div>     	
    </section>
  </main>
  	<div class="default-components" id="footer-components">
		<footer id="footer" class="footer-container">
  			<%@ include file="/app/common/footer.jsp" %>	
 		</footer>
	</div>
</body>
  <script src="${cp}/js/swiper_ReserveContainer.js"></script>
  <script src="${cp}/js/chatbot.js"></script>
  <script src="${cp}/js/designForInput.js"></script>
  
</html>