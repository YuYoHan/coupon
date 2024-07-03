<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="app.card.dao.CardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>마이리얼트립 :: 나다운 진짜 여행</title>
  <c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
  <link rel="shortcut icon" type="image/x-icon" href="${cp}/app/img/favicon.ico">
  <link rel="stylesheet" href="${cp}/css/common.css">
  <link rel="stylesheet" href="${cp}/css/pointContainer.css">
  <link rel="stylesheet" href="${cp}/css/index.css">
  <link rel="stylesheet" href="${cp}/css/ticket.css">
  <link rel="stylesheet" href="${cp}/css/mainInfo.css">
  <link rel="stylesheet" href="${cp}/css/footer.css">
  <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
</head>
<body>
	<c:if test="${param.datefilter != null}">
		<script>
			alert("예약이 완료되었습니다.");
		</script>
	</c:if>
  <!-- header시작 -->
  <%@ include file="/app/common/header.jsp" %>
  <!-- header끝 -->
  <!-- main 시작 -->
  <main>
    <section class="mainHeader">
		<%@ include file="/app/card/mainHeader.jsp" %>      
    </section>
    <section class="mainBanner">
		<%@ include file="/app/card/mainbanner.jsp" %>     	
    </section>
    <div class="content">
		<%@ include file="/app/card/ticket.jsp" %>  	    	 	
    </div>
    <div class="pointContainer">
    	<%@ include file="/app/card/pointContainer.jsp" %>
    </div>
    <section class="mainInfo">
    	<%@ include file="/app/card/mainInfo.jsp" %>	
    </section>
  </main>
  
  <!-- main 끝 -->
  <!-- footer 시작 -->
	<div class="default-components" id="footer-components">
		<footer id="footer" class="footer-container">
  			<%@ include file="/app/common/footer.jsp" %>	
 		</footer>
	</div>
  <!-- footer 끝 -->
  <script src="${cp}/js/swiper_container.js"></script>
  <script src="${cp}/js/chatbot.js"></script>
  <script src="${cp}/js/designForInput.js"></script>
  <script src="${cp}/js/myinfo.js"></script>

</body>
</html>