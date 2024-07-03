<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="app.air.dao.AirDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
	<div class="popular" id="popular_destination_div">
		<h3 class="popular-title">제주 휴가 즐겨요 🏝️</h3>
			<div class="popular-container popular-destination ">
				<div class="swiper-wrapper popular-wrap" id="popular_wrap">
					<c:forEach items="${jejuTicketList}" var="ben">
	                		<div class=" swiper-slide popular-Items">
	                			<div class="popular-img" style="background-image:url(${ben.img_url})"></div>
		                		<div class="bottom">
		                		<span class="nation">${ben.nation}</span>
		                		<span class="trip">${ben.trip}</span>
		                		<span class="date">${ben.airtime}</span>
		                		<span class="price">${ben.price}
		                		<em class="airline">${ben.airline }</em>
		                		</span>
		                		</div>	               
		              	 	</div>  
	               	</c:forEach>
				</div>
			</div>
		</div>
